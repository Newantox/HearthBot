package Game.Auras;

import Game.MyTurnState;
import Game.TargetsType;
import Game.Buffs.AdditiveBuff;
import Game.Minions.Minion;
import Game.Minions.Race;

public class OldMurkEyeAura extends Aura {
	
	@Override
	public MyTurnState apply(MyTurnState oldstate, Minion source, Minion target) {
		if ((target.getRace()).equals(Race.MURLOC)) {
			return oldstate.applyBuff(source.getId(),new AdditiveBuff(getId(),1,0,0));
		}
		else return oldstate;
	}
	
	@Override
	public MyTurnState remove(MyTurnState oldstate, Minion source, Minion target) {
		return oldstate.removeBuff(source.getId(),getId());
	}

	@Override
	public TargetsType getEffectRange() {
		return TargetsType.ALLMINIONS;
	}

}
