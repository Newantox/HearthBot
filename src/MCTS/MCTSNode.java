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
	private double value;
	
	private int estWins;
	private int visits;
	private boolean bias;

	public MCTSNode(MCTSNode parent, Action action, State state, boolean bias) {
		super(parent,action,state);
		this.setState(state);
		this.setParent(parent);
		this.setAction(action);
		this.actions = state.getApplicableActions(true);
		this.children = new LinkedHashSet<MCTSNode>();
		this.value = state.getValue(this, 0.75, 0.25);
		this.bias = bias;
	}
	
	public MCTSNode selectBestChild() {
		MCTSNode currentBest = null;
		double bestValue = Double.MIN_VALUE;
	
		for (MCTSNode child : children) {
			double uctValue;
			if (bias) uctValue = 100 + ((double) child.getEstWins()/child.getVisits()) + (Math.sqrt(2*Math.log(visits)/child.getVisits())) - (double) 0.01*child.getValue()/(child.getVisits()+1);
			else uctValue = 100 + ((double) child.getEstWins()/child.getVisits()) + (Math.sqrt(2*Math.log(visits)/child.getVisits()));
			if (uctValue>bestValue) {
				currentBest = child;
				bestValue = uctValue;
			}
			//else System.out.println("No"+uctValue+","+child.getVisits()+","+child.getValue()+","+(Math.sqrt(2*Math.log(visits)/child.getVisits() - ((double) 0.01*child.getValue()/(child.getVisits()+1)))));
		}
		if (currentBest==null) {
			state.print();
			if (actions.isEmpty()) {System.out.println("empty actions"); System.out.println(children.size());}
			else System.out.println("Actions available");
			if (children.size()==1) currentBest = children.iterator().next();
		}
		if (currentBest.getAction()==null) throw new Error("best action is null");
		return currentBest;
	}
	
	public MCTSNode mostVisitedChild() {
		int max = -1;
		MCTSNode maxVisited = null;
		for (MCTSNode child : children) {
			if (child.getVisits() > max) {max = child.getVisits(); maxVisited = child;}
		}
		return maxVisited;
	}
	
	public MCTSNode Expand() {
		while (!actions.isEmpty()) {
			Action move = (actions.iterator()).next();
			
			if (!move.equals(null)) {
				MCTSNode child = new MCTSNode(this,move,getState().getActionResult(move),bias);
				children.add(child);
				actions.remove(move);
				return child;
			}
			else actions.remove(move);
		}
		return null;
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

	public double getValue() {
		return value;
	}
	
	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

}
