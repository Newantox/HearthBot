package Search;

public class AStarFunction implements NodeFunction {

	NodeFunction hn;
	
	public AStarFunction(NodeFunction heuristicFunction) {
		this.hn = heuristicFunction;
	}

	@Override
	public double getValue(Node n,double minionWeight, double hpWeight) {
		return n.gn + hn.getValue(n,minionWeight,hpWeight);
	}

}
