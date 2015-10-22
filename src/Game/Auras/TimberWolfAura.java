package Game.Auras;

import Game.MyTurnState;
import Game.Buffs.AdditiveBuff;
import Game.Minions.Minion;
import Game.Minions.Race;

public class TimberWolfAura extends Aura {
	
	private double id;

	public TimberWolfAura() {
		id = Math.random();
	}

	@Override
	public MyTurnState apply(MyTurnState oldstate, Minion source, Minion target) {
		if ((target.getRace()).equals(Race.BEAST) && ((source.getMyPos()<7 && target.getMyPos()<7) || (source.getMyPos()>=7 && target.getMyPos()>=7))) return oldstate.applyBuff(target.getId(),new AdditiveBuff(id,1,0,0));
		else return oldstate;
	}

}
