package Game;

import Search.GoalTest;
import Search.State;

public class GameGoalTest implements GoalTest {

    @Override
	public boolean isGoal(State state) {
		return state.isGameWon();
	}

}
