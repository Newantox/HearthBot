package Main;

import java.util.ArrayList;
import java.util.Stack;

import Game.BoardState;
import Game.GamePlay;
import Game.MulliganState;
import Game.MyTurnState;
import Game.TargetsType;
import Game.ViewType;
import Game.Actions.EndTurn;
import Game.Heroes.Hero;
import Game.Minions.Minion;
import Search.Node;
import Search.State;

//KEEP UPDATING PLAYER HEROS
public class PlayGame {
	
	private Player player1;
	private Player player2;
	int timer = 0;
	
	GamePlay gameplay = new GamePlay();
	
	public PlayGame(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
	}
	
	public int play(boolean real, MyTurnState state) {
		MyTurnState currentState;
		int turncount = 1;
		Node solution;
		if (state==null) {
			BoardState startState = setUpGame();
		
			State mulliganStates = new MulliganState(startState);
			mulliganStates.print();
			solution = player2.getSolution(mulliganStates);
		
			startState = (BoardState) perform(mulliganStates,solution);
		
			startState.print();
		
			startState = swapSides(real,startState);
		
			mulliganStates = new MulliganState(startState);
		
			solution = player1.getSolution(mulliganStates);
		
			startState = (BoardState) perform(mulliganStates,solution);
	
			startState.print();
		
			//currentState = startState.doStartTurnEffects(startState.getHero());
			//currentState = currentState.resolveRNG(true);
			currentState = startState;
		}
		else {
			BoardState startState = (BoardState) state.resolveRNG(false);
			if (startState.getHero().getTotalMana()<1) {
				startState = swapSides(real,startState);
				
				State mulliganStates = new MulliganState(startState);
			
				solution = player2.getSolution(mulliganStates);
			
				startState = (BoardState) perform(mulliganStates,solution);
		
			
				startState = swapSides(real,startState);
			
				currentState = startState.doStartTurnEffects(startState.getHero());
				currentState = currentState.resolveRNG(true);
			}
			else  currentState = startState;
		}
		
		while (true) {
			System.out.println("Player 1 turn "+turncount);
			System.out.println("---------------------------------------------------------------------");
			currentState.print();
			System.out.println("---------------------------------------------------------------------");
			timer = 0;
			while(!currentState.isTurnEnded()) {
				timer++;

				solution = player1.getSolution(currentState.viewBiased());
				currentState = perform(currentState,solution);

				if (((BoardState) currentState)==null) throw new Error("Null from perform");
				if (((BoardState) currentState).getEnemy().getHP()<=0) {
					if (real) gameplay.setWinner(1);
					return 1;
				}
				if (((BoardState) currentState).getHero().getHP()<=0) {
					if (real) gameplay.setWinner(2); 
					return 2;
				}
				if (timer>100) {currentState = currentState.getActionResult(new EndTurn()); currentState = currentState.resolveRNG(true); break;}
			} 
			timer = 0;
			
			System.out.println("Player 1 ended turn");
			System.out.println("---------------------------------------------------------------------");
			currentState.print();
			System.out.println("---------------------------------------------------------------------");
			currentState.setTurnEnded(false);
			currentState = swapSides(real, (BoardState) currentState);
	
			System.out.println("");

			System.out.println("Player 2 turn "+turncount);
			System.out.println("---------------------------------------------------------------------");
			currentState.print();
			System.out.println("---------------------------------------------------------------------");
			
			while(!currentState.isTurnEnded()) {
				timer++;
				solution = player2.getSolution(currentState.viewBiased());
				currentState = perform(currentState,solution);
				if (((BoardState) currentState).getEnemy().getHP()<=0) {
					if (real) gameplay.setWinner(2);
					return 2;
				}
				if (((BoardState) currentState).getHero().getHP()<=0) {
					if (real) gameplay.setWinner(1); 
					return 1;
				}
				if (timer>100) {currentState = currentState.getActionResult(new EndTurn()); currentState = currentState.resolveRNG(true); break;}
			}
			
			System.out.println("Player 2 ended turn");
			System.out.println("---------------------------------------------------------------------");
			currentState.print();
			System.out.println("---------------------------------------------------------------------");
			
			currentState.setTurnEnded(false);
			currentState = swapSides(real, (BoardState) currentState);
			turncount+=1;
		}
	}
		
	public BoardState swapSides(boolean real, BoardState config) {
		Hero newHero = config.getEnemy().fresh();
		newHero.setSide(TargetsType.ALLYCHAR);
		
		Hero newEnemy = config.getHero().fresh();
		newEnemy.setSide(TargetsType.ENEMYCHAR);
		
		ArrayList<Minion> newMySide = new ArrayList<Minion>();
		
		for (Minion minion : config.getOppSide()) {
			Minion newMinion = new Minion(minion);
			newMySide.add(newMinion);
		}
		
		ArrayList<Minion> newOppSide = new ArrayList<Minion>();
		
		for (Minion minion : config.getMySide()) {
			Minion newMinion = new Minion(minion);
			newOppSide.add(newMinion);
		}
		
		int newEnemyHandSize = (config.getHero()).getMyHandSize();
		
		BoardState tempstate =  new BoardState(config.getViewType(),newHero,newEnemy,newOppSide,newMySide,config.getIdsInPlayOrder(),newEnemyHandSize,false,config.getIdCounter());
		tempstate.setTurnEnded(false);
		
		if (real) gameplay.addNewTurn(tempstate);
		return tempstate;
	}
	
	public MyTurnState perform(State startState,Node solution) {
		if (solution == null) throw new Error("Null solution");
			
		else {
			Stack<Node> stack = new Stack<Node>();
			Node node = solution;
			while (node != null) {
				stack.push(node);
				node = node.parent;
			}
			
			MyTurnState currentState;
			
			if(stack.isEmpty()) return (MyTurnState) startState;
			else {
				node = stack.pop();
				currentState = startState.getActionResult(node.action);
				currentState = currentState.resolveRNG(true);
				gameplay.addNewStep(currentState,node.action);
			}
			
			while (!stack.isEmpty()) {
				node = stack.pop();
				if (currentState.getActionResult(node.action)==null) {System.out.print("Problem with "); node.action.print();}
				node.action.print();
				currentState = currentState.getActionResult(node.action);
				currentState = currentState.resolveRNG(true);
				gameplay.addNewStep(currentState,node.action);
		
			}
			return currentState;
		}	
	}
	
	public BoardState setUpGame() {
		
		MyTurnState startState = new BoardState(ViewType.UNBIASED, player1.getInitialHero(), player2.getInitialHero(), new ArrayList<Minion>(), new ArrayList<Minion>(), new ArrayList<Integer>(), 0,true,0);
		startState = swapSides(true,(BoardState) startState);
		
		for (int i = 0; i<4; i++) {
			startState = startState.drawCard();
			startState = startState.resolveRNG(true);
		}
		for (int i = 0; i<3; i++) {
			startState = startState.enemyDrawCard();
			startState = startState.resolveRNG(true);
		}
		gameplay.addNewTurn(startState);
		startState.print();
		return (BoardState) startState;
	}

}
