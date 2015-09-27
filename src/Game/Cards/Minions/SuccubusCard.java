package Game.Cards.Minions;

import Game.Minions.Succubus;
import Game.Minions.Minion;

public class SuccubusCard extends MinionCard {

	public SuccubusCard() {
		super("Succubus",2);
	}
	
	public Minion makeNew(int target) {
		return new Succubus(target);
	}

}