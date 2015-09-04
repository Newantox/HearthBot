package Search;

import Game.BoardState;
import Game.ChoiceState;
import Game.IndexAction;
import Game.RandomState;
import Game.StateProbabilityPair;

public class Node {
	public final Node parent;
	public final Action action;
	public final State state;
	public int depth;
	public double fn;
	public double gn;
	public double best;
	public Node bestNode;
	
	public Node(Node parent, Action action, State state) {
		this.parent = parent;
		this.action = action;
		this.state = state;
		if (parent == null) {this.depth = 0; this.gn = 0;}
		else { this.depth = parent.depth + 1; this.gn = action.cost() + parent.gn;}
	}
	
	public double getBestValue() {
		double best;
		switch (state.getStatetype()) {
			case BOARD:
				BoardState boardstate = (BoardState) state;
				best = fn;
				for (Action action : boardstate.getApplicableActions()) {
					Node newnode = new Node(this,action,boardstate.getActionResult(action));
					if (newnode.getBestValue() < best) {best = newnode.getBestValue(); bestNode = newnode;}
				}
				
			case RANDOM:
				RandomState randomstate = (RandomState) state;
				best = 0;
				for (int i = 0; i < ((RandomState) randomstate).getSize(); i++) {
					StateProbabilityPair pair = ((RandomState) randomstate).getPair(i);
					Node pairnode = new Node(this,new IndexAction(i),pair.getState());
					best += pair.getProbability() * pairnode.getBestValue();
				}
				bestNode = this;
				
			case CHOICE:
				ChoiceState choicestate = (ChoiceState) state;
				best = 1000;
				for (Action action : state.getApplicableActions()) {
					Node newnode = new Node(this,action,state.getActionResult(action));
					if (newnode.getBestValue() < best) {best = newnode.getBestValue(); bestNode = newnode;}
				}
		
			default:
				best = 0;
		}
		return best;
	}

	public Node getBestNode() {
		return bestNode;
	}

	public void setBestNode(Node bestNode) {
		this.bestNode = bestNode;
	}
}


