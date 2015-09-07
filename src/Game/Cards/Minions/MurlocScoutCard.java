package Game.Cards.Minions;

import Game.Minions.Minion;
import Game.Minions.MurlocScout;

public class MurlocScoutCard extends MinionCard {
	
	public MurlocScoutCard() {
		super("Murloc Scout", 0);
	}

	@Override
	protected Minion makeNew(int target) {
		return new MurlocScout(target);
	}
}
