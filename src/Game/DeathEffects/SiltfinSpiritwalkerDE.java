package Game.DeathEffects;

import Game.MyTurnState;
import Game.TargetsType;
import Game.Minions.Minion;
import Game.Minions.Race;

public class SiltfinSpiritwalkerDE extends DeathEffect {
	
	public SiltfinSpiritwalkerDE() {
		
	}
	
	@Override
	public MyTurnState perform(MyTurnState oldstate, Minion source, Minion minion) {
		if (minion.getRace().equals(Race.MURLOC)) return oldstate.drawCard();
		else return oldstate;
	}

	@Override
	public TargetsType getEffectRange() {
		return TargetsType.ALLMINIONS;
	}

}
