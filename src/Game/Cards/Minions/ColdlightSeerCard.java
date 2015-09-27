package Game.Cards.Minions;

import Game.Minions.ColdlightSeer;
import Game.Minions.Minion;

public class ColdlightSeerCard extends MinionCard {

	public ColdlightSeerCard() {
		super("Coldlight Seer", 3);
	}

	@Override
	protected Minion makeNew(int target) {
		return new ColdlightSeer(target);
	}
	
}
