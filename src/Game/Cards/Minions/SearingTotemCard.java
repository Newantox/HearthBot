package Game.Cards.Minions;

import Game.Minions.SearingTotem;
import Game.Minions.Minion;

public class SearingTotemCard extends MinionCard {

	public SearingTotemCard() {
		super("SearingTotem",1);
	}
	
	public Minion makeNew(int target) {
		return new SearingTotem(target);
	}

}