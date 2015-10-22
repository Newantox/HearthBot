package Search;

import java.util.LinkedHashSet;
import java.util.Set;

import Game.GameGoalTest2;
import Game.StopWatch;

public class RandomPlayout implements Search {

	@Override
	public Node solution(State initialConfig, GoalTest goalTest,int step) {
		State currentState = initialConfig;
		Node currentNode = new Node(null,null,currentState);
		
		while (!currentState.getApplicableActions().isEmpty()) {
			Action action = takeRandom(currentState.getApplicableActions());
			currentState = currentState.getActionResult(action);
			currentNode = new Node(new Node(currentNode),action,currentState);
			if (currentState.isGameWon()) return currentNode;
		}
		return currentNode;
	}

	public Action takeRandom(Set<Action> set) {
		Set<Action> holder = set;
		double number = Math.random();
		Action backup = null;
		for (Action action : set) {
			backup = action;
			if (number > 1.0/holder.size()) {
				number -= 1.0/holder.size();
			}
			else return action;
		}
		return backup;
	}

	@Override
	public int lastSearch() {
		// TODO Auto-generated method stub
		return 0;
	}

}
