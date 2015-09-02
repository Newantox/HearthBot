package Game.Cards.Minions;

import Game.Minions.Minion;
import Game.Minions.ArgentSquire;

public class ArgentSquireCard extends MinionCard {

	public ArgentSquireCard() {
		super("Argent Squire",1);
	}
	
	public Minion makeNew(int target) {
		return new ArgentSquire(target);
	}

}

