package Search;

import java.util.LinkedHashSet;

import Game.GameGoalTest2;
// Change to record best solution then return best solution available, so not always trying to clear board.
public class GraphSearch implements Search {

	private Frontier frontier;
	private LinkedHashSet<State> exploredSet;
	private int lastSearch = 0;
	private State init;

	public GraphSearch(Frontier frontier) {
		this.frontier = frontier;
	}

	@Override
	public Node solution(State initialConfig, GoalTest goalTest,int step) {
		this.init = initialConfig;
		int k = 1;
		frontier.add(new Node(null,null,initialConfig));
		exploredSet = new LinkedHashSet<State>();
		while(!frontier.empty()) {
			Node n = frontier.remove();
			if (goalTest.isGoal(n.state)) {lastSearch = k; return n;}
			for (Action action : n.state.getApplicableActions()) {
				State newState = n.state.getActionResult(action);
				if (!exploredSet.contains(newState)) {k+=1; frontier.add(new Node(n, action, newState)); exploredSet.add(newState);}
			}
		}
		lastSearch = k;
		if (step<2) {frontier.clear(); Search g = new GraphSearch(frontier); return g.solution(init, new GameGoalTest2(), step+1);}
		else return null;
	}

	@Override
	public int lastSearch() {
		return lastSearch;
	}

}
