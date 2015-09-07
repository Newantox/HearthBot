package Game.Battlecrys;

import Game.BoardState;
import Game.Minions.Minion;
import Search.State;

public abstract class MinionBattlecry extends Battlecry {
	
	public State trigger(Minion minion, State oldstate) {
		return oldstate.performBC(this,minion);
	}
	
	public abstract State perform(Minion minion, BoardState oldstate);
	
}
