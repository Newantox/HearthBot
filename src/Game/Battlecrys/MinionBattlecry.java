package Game.Battlecrys;

import Game.BoardState;
import Game.MyTurnState;
import Game.Minions.Minion;

public abstract class MinionBattlecry extends Battlecry {
	
	public MyTurnState trigger(Minion minion, MyTurnState oldstate) {
		return oldstate.performBC(this,minion);
	}
	
	public abstract MyTurnState perform(Minion minion, BoardState oldstate);
	
}
