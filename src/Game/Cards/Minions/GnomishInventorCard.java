package Game.Cards.Minions;

import Game.Minions.GnomishInventor;
import Game.Minions.Minion;

public class GnomishInventorCard extends MinionCard {

	public GnomishInventorCard() {
		super("Gnomish Inventor",4);
	}
	
	public Minion makeNew(int target) {
		return new GnomishInventor(target);
	}

}