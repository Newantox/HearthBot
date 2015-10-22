package MCTS;

import java.util.LinkedHashSet;

import Game.GameGoalTest2;
import Game.StopWatch;
import Search.Action;
import Search.Frontier;
import Search.GoalTest;
import Search.Node;
import Search.Search;
import Search.State;

public class MCTS implements Search {

	private Frontier frontier;
	private LinkedHashSet<State> exploredSet;
	private int lastSearch = 0;
	private State init;

	public MCTS(Frontier frontier) {
		this.frontier = frontier;
	}

	@Override
	public MCTSNode solution(State initialConfig, GoalTest goalTest,int step) {
		StopWatch timer = new StopWatch();
		//MCTSNode best = new Node(null,null,null);
		double bestscore = 1000;
		MCTSNode currentNode = new MCTSNode(initialConfig,null,null);
		
		while (!currentNode.isLeaf()) {
			currentNode = currentNode.selectChild();
			if (currentNode.state.isGameWon()) return currentNode;
		}
		
	}

	@Override
	public int lastSearch() {
		return lastSearch;
	}

}
