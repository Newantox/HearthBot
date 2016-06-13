package Game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import Game.Actions.SwapCards;
import Search.Action;
import Search.Node;
import Search.State;

public class MulliganState implements State {
	
	private BoardState state;
	
	public MulliganState(BoardState state) {
		this.state = state;
	}

	@Override
	public Set<Action> getApplicableActions(boolean end) {
		Set<Action> actions = new LinkedHashSet<Action>();
		Hand hand = (state.getHero()).getMyHand();
		for (Set<Integer> positions : powerSetOfPositions(hand)) {
			int improvement = 0;
			for (int position : positions) if (!goodStart((hand.raw()).get(position))) improvement += -1;
			
			actions.add(new SwapCards(positions,improvement));
		}
		
		return actions;
		
	}

	@Override
	public MyTurnState getActionResult(Action action) {
		if (action==null) return state;
		return action.result(state);
	}

	@Override
	public double getValue(Node n,double minionWeight, double hpWeight) {
		return state.getValue(n,minionWeight,hpWeight);
	}

	@Override
	public double getBestValue(Node node, double minionWeight, double hpWeight) {
		System.out.println("Mulligan");
		double best = 1000;
		 for (Action action : getApplicableActions(true)) {
			 Node newnode = new Node(node,action,getActionResult(action));
			 if (newnode.getBestValue(minionWeight,hpWeight) < best) {
				 best = newnode.getBestValue(minionWeight,hpWeight);
				 node.bestNode = newnode;
				 node.best = best;
			 }
		  }
		 return best;
	}

	@Override
	public boolean isGameWon() {
		return false;
	}
	
	private boolean goodStart(PlayableCard card) {
		if (card.getCost()<=3) return true;
		else return false;
	}

	@Override
	public void print() {
		System.out.println("                  Mulligan");
		System.out.println("");
		((state.getHero()).getMyHand()).print();
		
	}
	
	@Override
	public String output() {
		
		String s = "<html><center><br>"+("                  Mulligan");
		s = s+"<br>";
		s = s+"<br>";
		s = s+((state.getHero()).getMyHand()).output();
		return s+"</center></html>";
	}
	
	public Set<Set<Integer>> powerSetOfPositions(Hand hand) {
		Set<Integer> setOfInts = new HashSet<Integer>();
		
		for (int i = 0; i<hand.getSize(); i++) setOfInts.add(i);
				
		return powerSet(setOfInts);
		
	}
	
	//Borrowed from StackExchange
	public static <T> Set<Set<T>> powerSet(Set<T> originalSet) {
	    Set<Set<T>> sets = new HashSet<Set<T>>();
	    if (originalSet.isEmpty()) {
	    	sets.add(new HashSet<T>());
	    	return sets;
	    }
	   ArrayList<T> list = new ArrayList<T>(originalSet);
	    T head = list.get(0);
	    Set<T> rest = new HashSet<T>(list.subList(1, list.size())); 
	    for (Set<T> set : powerSet(rest)) {
	    	Set<T> newSet = new HashSet<T>();
	    	newSet.add(head);
	    	newSet.addAll(set);
	    	sets.add(newSet);
	    	sets.add(set);
	    }		
	    return sets;
	}

	@Override
	public boolean isTurnEnded() {
		return false;
	}
	
}
