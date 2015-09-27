package Game.Cards.Minions;

import Game.Minions.DalaranMage;
import Game.Minions.Minion;

public class DalaranMageCard extends MinionCard {

	public DalaranMageCard() {
		super("Dalaran Mage",3);
	}
	
	public Minion makeNew(int target) {
		return new DalaranMage(target);
	}

}