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
		
		State mulliganStates = new MulliganState(startState);
		Node solution = player2.getSolution(mulliganStates);
		
		startState = (BoardState) perform(mulliganStates,solution);
		
		startState = swapSides(startState);
		
		mulliganStates = new MulliganState(startState);
		
		solution = player1.getSolution(mulliganStates);
		
		startState = (BoardState) perform(mulliganStates,solution);
		
		startState = swapSides(startState);
		
		MyTurnState currentState = startState;
		
		while (true) {
			while(!currentState.isTurnEnded()) {
				solution = player2.getSolution(currentState.viewBiased());
				currentState = perform(currentState,solution);
				if (player2.goalTest(currentState)) return 2;
			}
			
			currentState.setTurnEnded(false);
			currentState = swapSides((BoardState) currentState);
			
			while(!currentState.isTurnEnded()) {
			solution = player1.getSolution(currentState.viewBiased());
				currentState = perform(currentState,solution);
				if (player1.goalTest(currentState)) return 1;
			}
			
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
		
		ArrayList<Integer> newPositions = new ArrayList<Integer>();
		
		for (int position : config.getPositionsInPlayOrder()) {
			if (position<7) newPositions.add(position+7);
			else newPositions.add(position-7);
		}
		
		
		int newEnemyHandSize = (config.getHero()).getMyHandSize();
		
		return new BoardState(config.getViewType(),newHero,newEnemy,newMySide,newOppSide,newPositions,newEnemyHandSize);
		
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
				if ((node.action)==(null)) node = stack.pop();
				currentState = startState.getActionResult(node.action);
				currentState = currentState.resolveRNG();
			}
			
			while (!stack.isEmpty()) {
				node = stack.pop();
			
				currentState = currentState.getActionResult(node.action);
				currentState = currentState.resolveRNG();
		
			}
			return currentState;
		}	
		return null;
	}
	
	public BoardState setUpGame() {
		MyTurnState startState = new BoardState(ViewType.UNBIASED, player2.getInitialHero(), player1.getInitialHero(), new ArrayList<Minion>(), new ArrayList<Minion>(), new ArrayList<Integer>(), ((player1.getInitialHero()).getMyHandSize()));
		System.out.println("Deck size: "+((BoardState) startState).getHero().getMyDeck().getSize());
		for (int i = 0; i<4; i++) {
			startState = startState.drawCard();
			startState = startState.resolveRNG();
		}
		for (int i = 0; i<3; i++) {
			startState = startState.enemyDrawCard();
			startState = startState.resolveRNG();
		}
		
		return (BoardState) startState;
	}

}
