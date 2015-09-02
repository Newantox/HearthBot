package Search;

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
				best = 1000;
				for (Action action : state.getApplicableActions()) {
					Node newnode = new Node(this,action,state.getActionResult(action));
					if (newnode.getBestValue() < best) {best = newnode.getBestValue(); bestNode = newnode;}
				}
				
			case RANDOM:
				RandomState state2 = (RandomState)state;
				best = 0;
				for (int i = 0; i < state2.getSize(); i++) {
					StateProbabilityPair pair = state2.getPair(i);
					Node pairnode = new Node(this,new IndexAction(i),pair.getState());
					best += pair.getProbability() * pairnode.getBestValue();
				}
				bestNode = this;
		
			default:
				best = 0;
		}
		return best;
	}
}
