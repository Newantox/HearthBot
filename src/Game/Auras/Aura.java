package Game.Auras;

import Game.MyTurnState;
import Game.Minions.Minion;

public abstract class Aura {

	public abstract MyTurnState apply(MyTurnState oldstate, Minion source, Minion target);
	public abstract MyTurnState remove(MyTurnState oldstate, Minion source, Minion target);
}
