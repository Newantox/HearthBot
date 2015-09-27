package Game.Cards.Minions;

import Game.Minions.SenjinShieldmasta;
import Game.Minions.Minion;

public class SenjinShieldmastaCard extends MinionCard {

	public SenjinShieldmastaCard() {
		super("Senjin Shieldmasta",4);
	}
	
	public Minion makeNew(int target) {
		return new SenjinShieldmasta(target);
	}

}