package Game.Deathrattles;

import Game.BoardState;
import Search.State;

public abstract class WeaponDeathrattle extends Deathrattle {
	
	public State trigger(State oldstate) {
		return oldstate.performDR(this);
	}

	public abstract State perform(BoardState oldstate);
	
}
