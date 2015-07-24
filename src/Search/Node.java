package Search;

public class Node {
	public final Node parent;
	public final Action action;
	public final State state;
	public int depth;
	public double fn;
	public double gn;
	
	public Node(Node parent, Action action, State state) {
		this.parent = parent;
		this.action = action;
		this.state = state;
		if (parent == null) {this.depth = 0; this.gn = 0;}
		else { this.depth = parent.depth + 1; this.gn = action.cost() + parent.gn;}
	}
}
