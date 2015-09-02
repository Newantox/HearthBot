package Game;

import Search.GoalTest;
import Search.State;

public class GameGoalTest implements GoalTest {

	@Override
	public boolean isGoal(State state) {
		switch (state.getStatetype()) {
			case BOARD:
				BoardState board = (BoardState)state;
				return (board.getEnemy().getHP() <= 0);
		
			case RANDOM:
				RandomState boardrand = (RandomState)state;
				for (int i = 0; i<boardrand.getSize(); i++) {
					if (!isGoal(boardrand.getPair(i).getState())) return false;
				}
				return true;
				
			case CHOICE:
				return isGoal(((ChoiceState) state).getState());
		
			default:
				return false;
		}
	}

}
