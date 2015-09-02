package Game.Inspires;

import Game.BoardState;
import Game.Minions.Minion;
import Search.State;

public abstract class Inspire {
	public abstract State perform(Minion minion, BoardState oldstate);
}
