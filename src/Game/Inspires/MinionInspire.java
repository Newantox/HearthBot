package Game.Inspires;

import Game.BoardState;
import Game.MyTurnState;
import Game.ViewType;
import Game.Minions.Minion;

public abstract class MinionInspire extends Inspire {

	public MyTurnState trigger(Minion minion, MyTurnState oldstate) {
		return oldstate.performInspire(this,minion);
	}
	
	public abstract MyTurnState perform(Minion minion, BoardState oldstate);
	
}
