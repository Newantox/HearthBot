package Game.Deathrattles;

import Game.BoardState;
import Game.MyTurnState;
import Game.ViewType;
import Game.Minions.Minion;

public abstract class MinionDeathrattle extends Deathrattle {
	
	public MyTurnState trigger(Minion minion, MyTurnState oldstate) {
		return oldstate.performDR(this,minion);
	}
	
	public abstract MyTurnState perform(Minion minion, BoardState oldstate);
	
}
