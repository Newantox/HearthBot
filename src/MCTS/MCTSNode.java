package MCTS;

import java.util.Set;

import Search.Action;
import Search.Node;
import Search.State;

public class MCTSNode extends Node {
	
	State state;
	private MCTSNode parent;
	private Action action;
	private Set<MCTSNode> children;
	
	private double estValue;
	private int visits;

	public MCTSNode(State state, MCTSNode parent, Action action) {
		this.state = state;
		this.parent = parent;
		this.action = action;
		for (Action move : state.getApplicableActions()) {
			children.add(new MCTSNode(state.getActionResult(move),this,move));
		}
	}
	
	public MCTSNode selectChild() {
		MCTSNode currentBest = null;
		double bestValue = Double.MIN_VALUE;
		for (MCTSNode child : children) {
			double uctValue = child.getEstValue() + Math.sqrt(2*Math.log(visits)/child.getVisits());
			if (uctValue>bestValue) {
				currentBest = child;
				bestValue = uctValue;
			}
		}
		return currentBest;
	}
	
	public boolean isLeaf() {
		return children.isEmpty();
	}
	
	public double getEstValue() {
		return estValue;
	}

	public void setEstValue(double estValue) {
		this.estValue = estValue;
	}

	public int getVisits() {
		return visits;
	}

	public void setVisits(int visits) {
		this.visits = visits;
	}

}
