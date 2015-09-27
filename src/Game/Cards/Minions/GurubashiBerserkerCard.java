package Game.Cards.Minions;

import Game.Minions.GurubashiBerserker;
import Game.Minions.Minion;

public class GurubashiBerserkerCard extends MinionCard {

	public GurubashiBerserkerCard() {
		super("Gurubashi Berserker",5);
	}
	
	public Minion makeNew(int target) {
		return new GurubashiBerserker(target);
	}

}