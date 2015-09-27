package Game.Battlecrys;

import Game.BoardState;
import Game.MyTurnState;

public abstract class WeaponBattlecry extends Battlecry {
	
	public MyTurnState trigger(MyTurnState oldstate) {
		return oldstate.performBC(this);
}
	
	public abstract MyTurnState perform(BoardState oldstate);
	
}
