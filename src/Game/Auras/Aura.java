package Game.Auras;

import Game.MyTurnState;
import Game.TargetsType;
import Game.Minions.Minion;

public abstract class Aura {
	
	private int id;
	
	public Aura() {
		id = (int) Math.ceil(Math.random()*10000);
	}

	public abstract MyTurnState apply(MyTurnState oldstate, Minion source, Minion target);
	
	public MyTurnState remove(MyTurnState oldstate, Minion source, Minion target) {
		return oldstate.removeBuff(target.getId(),target.getName(),getId());
	}

	public int getId() {
		return id;
	}

	public abstract TargetsType getEffectRange();
	
}
