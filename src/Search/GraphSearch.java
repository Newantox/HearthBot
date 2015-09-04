package Search;

import java.util.LinkedHashSet;

import Game.BoardState;
import Game.ChoiceState;
import Game.GameGoalTest2;
import Game.RandomState;
import Game.StateType;
import Game.StopWatch;
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
		StopWatch timer = new StopWatch();
		Node best = new Node(null,null,null);
		double bestscore = 1000;
		
		this.init = initialConfig;
		int k = 1;
		frontier.add(new Node(null,null,initialConfig));
		exploredSet = new LinkedHashSet<State>();
		while(!frontier.empty() && timer.elapsedTime() < 55) {
			Node n = frontier.remove();
			if (goalTest.isGoal(n.state)) {System.out.println("Game won"); lastSearch = k; return n;}
			if (n.getBestValue() < bestscore) {best = n.getBestNode(); bestscore = n.getBestValue();}
			switch (n.state.getStatetype()) {
				case BOARD:
					BoardState boardstate = (BoardState)n.state;
					for (Action action : boardstate.getApplicableActions()) {
						State newState = boardstate.getActionResult(action);
						if (!exploredSet.contains(newState)) {k+=1; frontier.add(new Node(n, action, newState)); exploredSet.add(newState);}
					}
				
				case RANDOM:
					RandomState randomstate = (RandomState)n.state;
					for (Action action : randomstate.getApplicableActions()) {
							State newState = randomstate.getActionResult(action);
							if (!exploredSet.contains(newState)) {k+=1; frontier.add(new Node(n, action, newState)); exploredSet.add(newState);}
					}
					
				case CHOICE:
					ChoiceState choicestate = (ChoiceState)n.state;
					for (Action action : choicestate.getApplicableActions()) {
						State newState = choicestate.getActionResult(action);
						if (!exploredSet.contains(newState)) {k+=1; frontier.add(new Node(n, action, newState)); exploredSet.add(newState);}
						
					}
					
				default:
					return null;
			}

		}
		lastSearch = k;
		//if (step<2) {frontier.clear(); Search g = new GraphSearch(frontier); return g.solution(init, new GameGoalTest2(), step+1);}
		return best;
	}

	@Override
	public int lastSearch() {
		return lastSearch;
	}

}
