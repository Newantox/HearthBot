package Game.Cards.Minions;

import Game.Minions.Minion;
import Game.Minions.ArgentSquire;

public class ArgentSquireCard extends MinionCard {
	private String name;
	private int cost;

	public ArgentSquireCard() {
		name = "Argent Squire";
		cost = 1;
	}
	
	public Minion makeNew(int target) {
		return new ArgentSquire(target);
	}

}

