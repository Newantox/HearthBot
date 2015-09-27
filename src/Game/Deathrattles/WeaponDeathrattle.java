package Game.Deathrattles;

import Game.BoardState;
import Game.MyTurnState;

public abstract class WeaponDeathrattle extends Deathrattle {
	
	public MyTurnState trigger(MyTurnState oldstate) {
		return oldstate.performDR(this);
	}

	public abstract MyTurnState perform(BoardState oldstate);
	
}
