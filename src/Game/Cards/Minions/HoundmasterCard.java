package Game.Cards.Minions;

import Game.Minions.Houndmaster;
import Game.Minions.Minion;

public class HoundmasterCard extends MinionCard {

	public HoundmasterCard() {
		super("Houndmaster",4);
	}
	
	public Minion makeNew(int target) {
		return new Houndmaster(target);
	}

}