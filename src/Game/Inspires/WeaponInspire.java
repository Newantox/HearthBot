package Game.Inspires;

import Game.BoardState;
import Search.State;

public abstract class WeaponInspire extends Inspire {

public State trigger(State oldstate) {
	return oldstate.performInspire(this);
}

public abstract State perform(BoardState oldstate);

}
