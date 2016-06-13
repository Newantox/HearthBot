package Search;

import java.util.LinkedHashSet;

import Game.GameGoalTest2;
import Game.StopWatch;

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
		/*Node n = frontier.remove();
		if (goalTest.isGoal(n.state)) {System.out.println("Game won"); lastSearch = k; return n;}
		double nbest = n.getBestValue();
		System.out.println(nbest);
		if (nbest < bestscore) {best = n.getBestNode(); bestscore = nbest;}
		while ((n.bestNode) != null && !(n.bestNode).equals(n)) n = n.bestNode;
		if (goalTest.isGoal(n.state)) System.out.println("Game Won");
		return n;*/
		while(!frontier.empty() && timer.elapsedTime() < 55) {
			Node n = frontier.remove();
			if (goalTest.isGoal(n.state)) {System.out.println("Game won"); lastSearch = k; return n;}
			double nbest = n.getValue(frontier.getMinionWeight(),frontier.getHpWeight());
			System.out.println(nbest);
			if (nbest < bestscore) {best = n; bestscore = nbest;}
			//switch (n.state.getStatetype()) {
			//	case BOARD:
				//	BoardState boardstate = (BoardState)n.state;
					for (Action action : n.state.getApplicableActions(true)) {
						State newState = n.state.getActionResult(action);
						if (!exploredSet.contains(newState)) {k+=1; frontier.add(new Node(n, action, newState)); exploredSet.add(newState);}
					}
				
				/*case RANDOM:
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
					return null;*/
			}

		lastSearch = k;
		//if (step<2) {frontier.clear(); Search g = new GraphSearch(frontier); return g.solution(init, new GameGoalTest2(), step+1);}
		return best; 
	}

}
