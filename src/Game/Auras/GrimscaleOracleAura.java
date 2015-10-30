package Game.Auras;

import Game.MyTurnState;
import Game.Buffs.AdditiveBuff;
import Game.Minions.Minion;
import Game.Minions.Race;

public class GrimscaleOracleAura extends Aura {
	
	@Override
	public MyTurnState apply(MyTurnState oldstate, Minion source, Minion target) {
		if ((target.getRace()).equals(Race.MURLOC)) return oldstate.applyBuff(target.getId(),new AdditiveBuff(getId(),1,0,0));
		else return oldstate;
	}

}
