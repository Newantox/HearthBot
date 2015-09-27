package Game;

import Search.GoalTest;
import Search.State;

public class GameGoalTest2 implements GoalTest {

	@Override
	public boolean isGoal(State state) {
		BoardState board = (BoardState)state;
		return (board.numberOfEnemyMinions()==0);
	}

}
