package Game.Minions;

import Game.DeathEffects.SiltfinSpiritwalkerDE;

public class SiltfinSpiritwalker extends Minion {
	
	public SiltfinSpiritwalker() {
		super("Siltfin Spiritwalker",-1,4,2,5);
		setRace(Race.MURLOC);
		addDeathEffect(new SiltfinSpiritwalkerDE());
	}
	
	public SiltfinSpiritwalker(Minion m) {
		super(m);
	}

}
