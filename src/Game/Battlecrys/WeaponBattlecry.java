package Game.Battlecrys;

import Game.BoardState;
import Search.State;

public abstract class WeaponBattlecry extends Battlecry {
	
	public State trigger(State oldstate) {
		return oldstate.performBC(this);
}
	
	public abstract State perform(BoardState tempstate);
	
}
