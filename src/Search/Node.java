package Search;

public class Node {
	public final Node parent;
	public final Action action;
	public final State state;
	public int depth;
	public double fn;
	public double gn;
	public double best;
	public Node bestNode;
	public boolean done;
	
	public Node(Node parent, Action action, State state) {
		this.parent = parent;
		this.action = action;
		this.state = state;
		this.done = false;
		if (parent == null) {this.depth = 0; this.gn = 0;}
		else { this.depth = parent.depth + 1; this.gn = action.cost() + parent.gn;}
	}
	
	public Node(Node n) {
		this.parent = n.parent;
		this.action = n.action;
		this.state = n.state;
		this.done = n.done;
		this.depth = n.depth;
		this.gn = n.gn;
	}
	
	public double getValue() {
		return state.getValue(this);
	}
	
	public double getBestValue() {
		if (!done) {
			done = true;
			return state.getBestValue(this);
		}
		else return best;
	}

	public Node getBestNode() {
		return bestNode;
	}

	public void setBestNode(Node bestNode) {
		this.bestNode = bestNode;
	}
}


