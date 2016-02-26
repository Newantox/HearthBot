package Search;

import java.util.Comparator;

public class NodeComparator implements Comparator<Node> {

	double minionWeight;
	double hpWeight;
	
	public NodeComparator(double minionWeight, double hpWeight) {
		this.minionWeight = minionWeight;
		this.hpWeight = hpWeight;
	}
	
	@Override
	public int compare(Node n1, Node n2) {
		return (int) Math.signum(n1.getValue(minionWeight,hpWeight)-n2.getValue(minionWeight,hpWeight));
	}

}
