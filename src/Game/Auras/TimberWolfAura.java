package Game.Auras;

import Game.MyTurnState;
import Game.TargetsType;
import Game.Buffs.AdditiveBuff;
import Game.Minions.Minion;
import Game.Minions.Race;

public class TimberWolfAura extends Aura {

	@Override
	public MyTurnState apply(MyTurnState oldstate, Minion source, Minion target) {
		if ((target.getRace()).equals(Race.BEAST)) return oldstate.applyBuff(target.getId(),target.getName(),new AdditiveBuff(getId(),1,0,0));
		else return oldstate;
	}

	@Override
	public TargetsType getEffectRange() {
		return TargetsType.ALLYMINIONS;
	}

}
