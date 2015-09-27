package Game.Minions;

import Game.DeathEffects.SiltfinSpiritwalkerDE;

public class SiltfinSpiritwalker extends Minion {
	
	public SiltfinSpiritwalker(int target) {
		super("Siltfin Spiritwalker",target,4,2,5);
		setRace(Race.MURLOC);
		addDeathEffect(new SiltfinSpiritwalkerDE());
	}
	
	public SiltfinSpiritwalker(Minion m) {
		super(m);
	}

}
