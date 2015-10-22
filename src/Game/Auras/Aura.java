package Game.Auras;

import Game.MyTurnState;
import Game.Minions.Minion;

public abstract class Aura {
	
	private double id;

	public abstract MyTurnState apply(MyTurnState oldstate, Minion source, Minion target);
	
	public MyTurnState remove(MyTurnState oldstate, Minion source, Minion target) {
		return oldstate.removeBuff(target.getId(),getId());
	}

	public double getId() {
		return id;
	}
}
