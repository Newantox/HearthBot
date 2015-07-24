package Game;

import Search.GoalTest;
import Search.State;

public class GameGoalTest2 implements GoalTest {

	@Override
	public boolean isGoal(State state) {
		BoardState board = (BoardState)state;
		for (int i = 0; i<7; i++) {
			if (board.getOppSide()[i] != null) return false;
		}
		return true;
	}

}
