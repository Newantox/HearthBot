package Main;

import java.util.ArrayList;
import java.util.Stack;

import Game.BoardState;
import Game.ChoiceState;
import Game.MyTurnState;
import Game.TargetsType;
import Game.Actions.EndTurn;
import Game.Heroes.Hero;
import Game.Minions.Minion;
import Search.Node;
import Search.Search;
import Search.State;

public class PlayOut {
	
	private Player player1;
	private Player player2;
	Node solution;
	private MyTurnState currentState;
	int timer = 0;
	int count = 0;
	
	ChoiceState temp = new ChoiceState();
	
	public PlayOut(MyTurnState state, Search search1, Search search2) {
		this.player1 = new Player(search1,null);
		this.player2 = new Player(search2,null);
		this.currentState = (state.resolveRNG(false)).predictUnbiased();
	}
	
	//public PlayOut(MulliganState state, Search search1, Search search2) {
	//	this.player1 = new Player(search1);
	//	this.player2 = new Player(search2);
	//	this.currentState = 
	//}
	
	public int play() {
		currentState = currentState.resolveRNG(false);
		while (true) {
			count++;
			//if (( ((BoardState) currentState).getHero()).getTotalMana()<10) currentState.print();
			//if (count==1000) currentState.print();
			//if (count>=10000) currentState.print();
			timer = 0;
			while(!currentState.isTurnEnded()) {
				timer++;
				solution = player1.getSolution(currentState.viewBiased());
				currentState = perform(currentState,solution);
				
				if (!currentState.getClass().equals(temp.getClass())) {
					if (((BoardState) currentState).getEnemy().getHP()<=0) {return 1;}
					if (((BoardState) currentState).getHero().getHP()<=0) {return 0;}
				}
				if (timer>100) {currentState = currentState.getActionResult(new EndTurn()); currentState = currentState.resolveRNG(false); break;}
			} 
			timer = 0;
		
			currentState.setTurnEnded(false);
			currentState = swapSides((BoardState) currentState);
			
			while(!currentState.isTurnEnded()) {
				timer++;
				solution = player2.getSolution(currentState.viewBiased());
				currentState = perform(currentState,solution);
				
				if (!currentState.getClass().equals(temp.getClass())) {
					if (((BoardState) currentState).getEnemy().getHP()<=0) {return 0;}
					if (((BoardState) currentState).getHero().getHP()<=0) {return 1;}
				}
				if (timer>100) {currentState = currentState.getActionResult(new EndTurn()); currentState = currentState.resolveRNG(true); break;}
			}
			
			currentState.setTurnEnded(false);
			currentState = swapSides((BoardState) currentState);
		}
	}
		
		public BoardState swapSides(BoardState config) {
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
			
			return tempstate;
		}
		
		public MyTurnState perform(State startState,Node solution) {
			if (solution == null) System.out.println("Problem simulating turn.");
				
			else {
				Stack<Node> stack = new Stack<Node>();
				Node node = solution;
				while (node.action != null) {
					stack.push(node);
					node = node.parent;
				}
				
				MyTurnState currentState;
				
				if(stack.isEmpty()) return (MyTurnState) startState;
				else {
					node = stack.pop();
					currentState = startState.getActionResult(node.action);
					currentState = currentState.resolveRNG(false);
				}
				
				while (!stack.isEmpty()) {
					node = stack.pop();
					if (currentState.getActionResult(node.action)==null) {System.out.print("Problem with "); node.action.print();}
					//node.action.print();
					currentState = currentState.getActionResult(node.action);
					currentState = currentState.resolveRNG(false);
			
				}
				return currentState;
			}	
			return null;
		}
}
