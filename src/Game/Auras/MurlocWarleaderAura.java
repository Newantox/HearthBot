package Game.Auras;

import Game.Minions.Minion;
import Game.Minions.Race;
import Game.MyTurnState;

public class MurlocWarleaderAura extends Aura {
	
	@Override
	public MyTurnState apply(MyTurnState oldstate, Minion source, Minion target) {
		if ((target.getRace()).equals(Race.MURLOC)) {
			return oldstate.changeAtkHP(target,2,1);
		}
		else return oldstate;
	}
	
	@Override
	public MyTurnState remove(MyTurnState oldstate, Minion source, Minion target) {
		if ((target.getRace()).equals(Race.MURLOC)) {
			return oldstate.changeAtkHP(target,-2,-1);
		}
		else return oldstate;
	}

}
