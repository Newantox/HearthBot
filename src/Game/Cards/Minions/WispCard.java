package Game.Cards.Minions;

import Game.Minions.Minion;
import Game.Minions.Wisp;

public class WispCard extends MinionCard {
	private String name;
	private int cost;

	public WispCard() {
		name = "Wisp";
		cost = 0;
	}
	
	public Minion makeNew(int target) {
		return new Wisp(target);
	}

}
