package Game.Deathrattles;

import Game.BoardState;
import Game.Minions.Minion;
import Search.State;

public abstract class Deathrattle {

	public State trigger(Minion minion, State oldstate) {
		return oldstate.performDR(this,minion);
	}

	public abstract State perform(Minion minion, BoardState boardState);
	
}
