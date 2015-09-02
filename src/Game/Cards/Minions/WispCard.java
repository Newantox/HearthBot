package Game.Cards.Minions;

import Game.Minions.Minion;
import Game.Minions.Wisp;

public class WispCard extends MinionCard {

	public WispCard() {
		super("Wisp",1);
	}
	
	public Minion makeNew(int target) {
		return new Wisp(target);
	}

}
