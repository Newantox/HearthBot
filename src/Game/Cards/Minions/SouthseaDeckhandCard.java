package Game.Cards.Minions;

import Game.Minions.SouthseaDeckhand;
import Game.Minions.Minion;

public class SouthseaDeckhandCard extends MinionCard {

	public SouthseaDeckhandCard() {
		super("Southsea Deckhand",1);
	}
	
	public Minion makeNew(int target) {
		return new SouthseaDeckhand(target);
	}

}

