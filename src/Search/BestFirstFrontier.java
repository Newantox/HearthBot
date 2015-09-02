package Search;

import java.util.PriorityQueue;

import Game.BoardState;

public class BestFirstFrontier implements Frontier  {
	
	private PriorityQueue<Node> frontier = new PriorityQueue<Node>(100 , new NodeComparator());
	private NodeFunction nfunction;
	private int max = 0;
	private int current = 0;

	public BestFirstFrontier(NodeFunction f) {
		this.nfunction = f;
	}

	@Override
	public void add(Node n) {
		n.fn = nfunction.getValue(n);
		current += 1;
		if (current>max) max = current;
		frontier.add(n);
		State state = n.state;
		//BoardState board = (BoardState)state;
		System.out.println(current);

	}

	@Override
	public void clear() {
		frontier.clear();

	}

	@Override
	public boolean empty() {
		return frontier.isEmpty();
	}

	@Override
	public Node remove() {
		if (current>0) {
			current -= 1;
			return frontier.remove();
		}
		return null;
	}

	@Override
	public int maxNodes() {
		return max;
	}

}
