package Game.Cards.Minions;

import Game.Minions.ShatteredSunCleric;
import Game.Minions.Minion;

public class ShatteredSunClericCard extends MinionCard {

	public ShatteredSunClericCard() {
		super("Shattered Sun Cleric",3);
	}
	
	public Minion makeNew(int target) {
		return new ShatteredSunCleric(target);
	}

}