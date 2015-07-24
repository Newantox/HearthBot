package Search;

public class AStarFunction implements NodeFunction {

	NodeFunction hn;
	
	public AStarFunction(NodeFunction heuristicFunction) {
		this.hn = heuristicFunction;
	}

	@Override
	public double getValue(Node n) {
		return n.gn + hn.getValue(n);
	}

}
