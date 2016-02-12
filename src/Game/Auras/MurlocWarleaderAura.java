package Game.Auras;

import Game.Buffs.AdditiveBuff;
import Game.Minions.Minion;
import Game.Minions.Race;
import Game.MyTurnState;
import Game.TargetsType;

public class MurlocWarleaderAura extends Aura {
	
	@Override
	public MyTurnState apply(MyTurnState oldstate, Minion source, Minion target) {
		if ((target.getRace()).equals(Race.MURLOC)) {
			return oldstate.applyBuff(target.getId(),target.getName(), new AdditiveBuff(getId(),2,1,0));
		}
		else return oldstate;
	}

	@Override
	public TargetsType getEffectRange() {
		return TargetsType.ALLMINIONS;
	}

}
