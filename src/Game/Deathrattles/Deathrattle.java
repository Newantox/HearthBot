package Game.Deathrattles;

import Game.BoardState;
import Game.Minions.Minion;
import Search.State;

public abstract class Deathrattle {
	public abstract State perform(Minion minion, BoardState oldstate);
}
