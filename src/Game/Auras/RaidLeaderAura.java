package Game.Auras;

import Game.MyTurnState;
import Game.TargetsType;
import Game.Buffs.AdditiveBuff;
import Game.Minions.Minion;

public class RaidLeaderAura extends Aura {

	@Override
	public MyTurnState apply(MyTurnState oldstate, Minion source, Minion target) {
		return oldstate.applyBuff(target.getId(),new AdditiveBuff(getId(),1,0,0));
	}

	@Override
	public TargetsType getEffectRange() {
		return TargetsType.ALLYMINIONS;
	}

}
