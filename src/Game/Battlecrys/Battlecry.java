package Game.Battlecrys;

import Game.BoardState;
import Game.Minions.Minion;
import Search.State;

public abstract class Battlecry {

	public State trigger(Minion minion, State oldstate) {
		return oldstate.performBC(this,minion);
	}
	
	public abstract State perform(Minion minion, BoardState boardState);

}
