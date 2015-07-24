package Search;

import java.util.Comparator;

public class NodeComparator implements Comparator<Node> {

	@Override
	public int compare(Node n1, Node n2) {
		return (int) (n1.fn - n2.fn);
	}

}
