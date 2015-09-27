package Game.Cards.Minions;

import Game.Minions.BootyBayBodyguard;
import Game.Minions.Minion;

public class BootyBayBodyguardCard extends MinionCard {

	public BootyBayBodyguardCard() {
		super("Booty Bay Bodyguard",5);
	}
	
	public Minion makeNew(int target) {
		return new BootyBayBodyguard(target);
	}

}