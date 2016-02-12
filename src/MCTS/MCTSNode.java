package MCTS;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import Search.Action;
import Search.Node;
import Search.State;

public class MCTSNode extends Node {
	
	private State state;
	private MCTSNode parent;
	private Action action;
	private Set<MCTSNode> children;
	private Set<Action> actions;
	
	private int estWins;
	private int visits;

	public MCTSNode(MCTSNode parent, Action action, State state) {
		super(parent,action,state);
		this.setState(state);
		this.setParent(parent);
		this.setAction(action);
		this.actions = state.getApplicableActions();
		this.children = new LinkedHashSet<MCTSNode>();
	}
	
	public MCTSNode selectBestChild() {
		MCTSNode currentBest = null;
		double bestValue = Double.MIN_VALUE;
		for (MCTSNode child : children) {
			double uctValue = ((double) child.getEstWins()/child.getVisits()) + Math.sqrt(2*Math.log(visits)/child.getVisits());
			if (uctValue>bestValue) {
				currentBest = child;
				bestValue = uctValue;
			}
		}
		if (currentBest==null) {
			state.print();
			if (actions.isEmpty()) System.out.println("empty actions");
			else System.out.println("Actions available");
		}
		return currentBest;
	}
	
	public MCTSNode Expand() {
		Action move = (actions.iterator()).next();
		MCTSNode child = new MCTSNode(this,move,getState().getActionResult(move));
		children.add(child);
		actions.remove(move);
		return child;
	}
	
	public boolean isLeaf() {
		return children.isEmpty() && actions.isEmpty();
	}
	
	public int getEstWins() {
		return estWins;
	}

	public void setEstWins(int estWins) {
		this.estWins = estWins;
	}

	public int getVisits() {
		return visits;
	}

	public void setVisits(int visits) {
		this.visits = visits;
	}

	public MCTSNode getParent() {
		return parent;
	}

	public void setParent(MCTSNode parent) {
		this.parent = parent;
	}
	
	public boolean fullyExpanded() {
		return (actions.isEmpty());
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

}
