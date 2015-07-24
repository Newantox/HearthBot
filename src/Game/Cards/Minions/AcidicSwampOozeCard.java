package Game.Cards.Minions;

import Game.Minions.AcidicSwampOoze;
import Game.Minions.Minion;

public class AcidicSwampOozeCard extends MinionCard {
	private String name;
	private int cost;

	public AcidicSwampOozeCard() {
		name = "Acidic Swamp Ooze";
		cost = 2;
	}
	
	public Minion makeNew(int target) {
		return new AcidicSwampOoze(target);
	}

}
