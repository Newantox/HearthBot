package Game.Auras;

import Game.Buffs.AdditiveBuff;
import Game.Minions.Minion;
import Game.Minions.Race;
import Game.MyTurnState;

public class MurlocWarleaderAura extends Aura {
	
	private double id;

	public MurlocWarleaderAura() {
		id = Math.random();
	}
	
	@Override
	public MyTurnState apply(MyTurnState oldstate, Minion source, Minion target) {
		if ((target.getRace()).equals(Race.MURLOC)) {
			return oldstate.applyBuff(target.getId(), new AdditiveBuff(id,2,1,0));
		}
		else return oldstate;
	}

}
