package Main;

import java.util.ArrayList;
import java.util.Stack;

import Game.BoardState;
import Game.MulliganState;
import Game.MyTurnState;
import Game.ViewType;
import Game.Heroes.Hero;
import Game.Minions.Minion;
import Search.Node;
import Search.State;

//kEEP UPDATING PLAYER HEROS
public class PlayGame {
	
	private Player player1;
	private Player player2;
	
	public PlayGame(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
	}
	
	public int play() {
		
		BoardState startState = setUpGame();
		System.out.println("Handsize "+startState.getHero().getMyHandSize()+","+startState.getEnemyHandSize());
		State mulliganStates = new MulliganState(startState);
		Node solution = player2.getSolution(mulliganStates);
		
		startState = (BoardState) perform(mulliganStates,solution);
		
		startState.print();
		
		startState = swapSides(startState);
		
		mulliganStates = new MulliganState(startState);
		
		solution = player1.getSolution(mulliganStates);
		
		startState = (BoardState) perform(mulliganStates,solution);
		
		System.out.println("Player 1 ended turn");
		startState.print();
		
		startState = swapSides(startState);
		
		MyTurnState currentState = startState;
		int turncount = 1;
		
		while (true) {
			System.out.println("Player 2 turn "+turncount);
			while(!currentState.isTurnEnded()) {
				if ( currentState.getApplicableActions().size()>0) System.out.println(currentState.getApplicableActions().size());
				solution = player2.getSolution(currentState.viewBiased());
				currentState = perform(currentState,solution);
				if (player1.goalTest(currentState)) return 1;
				if (player2.goalTest(currentState)) return 2;
			}
			System.out.println("Player 2 ended turn");
			currentState.print();
			currentState.setTurnEnded(false);
			currentState = swapSides((BoardState) currentState);
			currentState.print();
			turncount+=1;
			System.out.println("Player 1 turn "+turncount);
			while(!currentState.isTurnEnded()) {
				solution = player1.getSolution(currentState.viewBiased());
				currentState = perform(currentState,solution);
				if (player1.goalTest(currentState)) return 1;
				if (player2.goalTest(currentState)) return 2;
			}
			System.out.println("Player 1 ended turn");
			currentState.print();
			
			currentState.setTurnEnded(false);
			currentState = swapSides((BoardState) currentState);
		}
	}
		
	public BoardState swapSides(BoardState config) {
		Hero newHero = config.getEnemy().fresh();
		newHero.setMyPos(14);
		
		Hero newEnemy = config.getHero().fresh();
		newEnemy.setMyPos(15);
		
		ArrayList<Minion> newMySide = new ArrayList<Minion>();
		
		for (Minion minion : config.getOppSide()) {
			Minion newMinion = new Minion(minion);
			newMinion.setMyPos(newMinion.getMyPos()-7);
			newMySide.add(newMinion);
		}
		
		ArrayList<Minion> newOppSide = new ArrayList<Minion>();
		
		for (Minion minion : config.getOppSide()) {
			Minion newMinion = new Minion(minion);
			newMinion.setMyPos(newMinion.getMyPos()+7);
			newOppSide.add(newMinion);
		}
		
		int newEnemyHandSize = (config.getHero()).getMyHandSize();
		
		BoardState tempstate =  new BoardState(config.getViewType(),newHero,newEnemy,newMySide,newOppSide,config.getIdsInPlayOrder(),newEnemyHandSize);
		tempstate.setTurnEnded(false);
		
		return tempstate;
	}
	
	public MyTurnState perform(State startState,Node solution) {
		if (solution == null) System.out.println("Problem simulating turn.");
			
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
				currentState = currentState.resolveRNG();
			}
			
			while (!stack.isEmpty()) {
				node = stack.pop();
				if (currentState.getActionResult(node.action)==null) {System.out.print("Problem with "); node.action.print();}
				node.action.print();
				currentState = currentState.getActionResult(node.action);
				currentState = currentState.resolveRNG();
		
			}
			return currentState;
		}	
		return null;
	}
	
	public BoardState setUpGame() {
		
		MyTurnState startState = new BoardState(ViewType.UNBIASED, player1.getInitialHero(), player2.getInitialHero(), new ArrayList<Minion>(), new ArrayList<Minion>(), new ArrayList<Integer>(), 0);
		startState = swapSides((BoardState) startState);
		
		for (int i = 0; i<4; i++) {
			startState = startState.drawCard();
			startState = startState.resolveRNG();
			System.out.println("Hand size player 2 drawing"+((BoardState) startState).getHero().getMyHandSize()+","+((BoardState) startState).getEnemyHandSize());
		}
		for (int i = 0; i<3; i++) {
			startState = startState.enemyDrawCard();
			startState = startState.resolveRNG();
			System.out.println("Hand size player 1 drawing "+((BoardState) startState).getHero().getMyHandSize()+","+((BoardState) startState).getEnemyHandSize());
			
		}
		
		System.out.println("Hand size start"+((BoardState) startState).getHero().getMyHandSize()+","+((BoardState) startState).getEnemyHandSize());
		return (BoardState) startState;
	}

}
