package Game.Cards.Minions;

import Game.Minions.AcidicSwampOoze;
import Game.Minions.Minion;

public class AcidicSwampOozeCard extends MinionCard {

	public AcidicSwampOozeCard() {
		super("Acidic Swamp Ooze",2);
	}
	
	public Minion makeNew(int target) {
		return new AcidicSwampOoze(target);
	}

}
