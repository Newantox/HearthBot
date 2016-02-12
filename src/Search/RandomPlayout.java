package Search;

import java.util.LinkedHashSet;
import java.util.Set;

import Game.GameGoalTest2;
import Game.StopWatch;
import Game.Actions.EndTurn;

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
			else return currentNode;
		}
		return currentNode;
	}

	public Action takeRandom(Set<Action> set) {
		double number = Math.random();
		Action backup = new EndTurn();
		for (Action action : set) {
			if (number > 1.0/set.size()) {
				number -= 1.0/set.size();
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
