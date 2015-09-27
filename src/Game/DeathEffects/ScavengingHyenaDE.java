package Game.DeathEffects;

import Game.MyTurnState;
import Game.Minions.Minion;
import Game.Minions.Race;

public class ScavengingHyenaDE extends DeathEffect {
	
	public ScavengingHyenaDE() {
	}
	
	@Override
	public MyTurnState perform(MyTurnState oldstate, Minion source, Minion minion) {
		if (minion.getRace().equals(Race.BEAST) && ((source.getMyPos()<7 && minion.getMyPos()<7) || (source.getMyPos()>=7 && minion.getMyPos()>=7))) return oldstate.changeAtkHP(source, 2, 1);
		else return oldstate;
	}
	
}
