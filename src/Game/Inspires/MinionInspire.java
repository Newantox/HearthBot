package Game.Inspires;

import Game.BoardState;
import Game.Minions.Minion;
import Search.State;

public abstract class MinionInspire extends Inspire {

	public State trigger(Minion minion, State oldstate) {
		return oldstate.performInspire(this,minion);
	}
	
	public abstract State perform(Minion minion, BoardState oldstate);
	
}
