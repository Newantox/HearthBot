package GameSearch;

import Search.Node;
import Search.NodeFunction;

public class GameHeuristic implements NodeFunction {
	
	public double getValue(Node n,double minionWeight, double hpWeight) {
		return (n.state).getValue(n,minionWeight,hpWeight);
	}
			
}
