package Game.Cards.Minions;

import Game.Minions.Minion;
import Game.Minions.Hound;

public class HoundCard extends MinionCard {

	public HoundCard() {
		super("Hound",1);
	}
	
	public Minion makeNew(int target) {
		return new Hound(target);
	}

}