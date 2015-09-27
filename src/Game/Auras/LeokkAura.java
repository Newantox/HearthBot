package Game.Auras;

import Game.MyTurnState;
import Game.Minions.Minion;

public class LeokkAura extends Aura {

	@Override
	public MyTurnState apply(MyTurnState oldstate, Minion source, Minion target) {
		if ((source.getMyPos()<7 && target.getMyPos()<7) || (source.getMyPos()>=7 && target.getMyPos()>=7)) return oldstate.changeAtkHP(target,1,0);
		else return oldstate;
	}
	
	@Override
	public MyTurnState remove(MyTurnState oldstate, Minion source, Minion target) {
		if ((source.getMyPos()<7 && target.getMyPos()<7) || (source.getMyPos()>=7 && target.getMyPos()>=7)) return oldstate.changeAtkHP(target,-1,0);
		else return oldstate;
	}

}
