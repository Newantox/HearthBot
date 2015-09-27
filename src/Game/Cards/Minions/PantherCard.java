package Game.Cards.Minions;

import Game.Minions.Minion;
import Game.Minions.Panther;

public class PantherCard extends MinionCard {

	public PantherCard() {
		super("Panther",2);
	}
	
	public Minion makeNew(int target) {
		return new Panther(target);
	}

}