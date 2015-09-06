package Game;

import Search.GoalTest;
import Search.State;

public class GameGoalTest implements GoalTest {

    @Override
	public boolean isGoal(State state) {
		return isGoalCheck(state);
	}
	
	private boolean isGoalCheck(BoardState boardState) {
		return (boardState.getEnemy().getHP() <= 0);
	}
	
	private boolean isGoalCheck(RandomState randomState) {
		for (int i = 0; i<randomState.getSize(); i++) {
			if (!isGoal(randomState.getPair(i).getState())) return false;
		}
		return true;
	}
	
	private boolean isGoalCheck(ChoiceState state) {
		return false;
	}
	
	private boolean isGoalCheck(State state) {
		return false;
	}
}
