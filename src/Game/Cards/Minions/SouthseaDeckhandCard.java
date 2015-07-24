package Game.Cards.Minions;

import Game.Minions.SouthseaDeckhand;
import Game.Minions.Minion;

public class SouthseaDeckhandCard extends MinionCard {
	private String name;
	private int cost;

	public SouthseaDeckhandCard() {
		name = "Southsea Deckhand";
		cost = 1;
	}
	
	public Minion makeNew(int target) {
		return new SouthseaDeckhand(target);
	}

}

