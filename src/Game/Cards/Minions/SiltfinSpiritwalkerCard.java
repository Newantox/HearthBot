package Game.Cards.Minions;

import Game.Minions.Minion;
import Game.Minions.SiltfinSpiritwalker;

public class SiltfinSpiritwalkerCard extends MinionCard {

	public SiltfinSpiritwalkerCard() {
		super("Siltfin Spiritwalker", 4);
	}

	@Override
	protected Minion makeNew(int target) {
		return new SiltfinSpiritwalker(target);
	}
	
}
