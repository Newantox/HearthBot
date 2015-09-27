package Game.Auras;

import Game.MyTurnState;
import Game.Minions.Minion;
import Game.Minions.Race;

public class GrimscaleOracleAura extends Aura {

	@Override
	public MyTurnState apply(MyTurnState oldstate, Minion source, Minion target) {
		if ((target.getRace()).equals(Race.MURLOC)) return oldstate.changeAtkHP(target,1,0);
		else return oldstate;
	}
	
	@Override
	public MyTurnState remove(MyTurnState oldstate, Minion source, Minion target) {
		if ((target.getRace()).equals(Race.MURLOC)) return oldstate.changeAtkHP(target,-1,0);
		else return oldstate;
	}

}
