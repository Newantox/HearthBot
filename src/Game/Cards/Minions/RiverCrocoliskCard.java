package Game.Cards.Minions;

import Game.Minions.Minion;
import Game.Minions.RiverCrocolisk;

public class RiverCrocoliskCard extends MinionCard {

	public RiverCrocoliskCard() {
		super("River Crocolisk",2);
	}
	
	public Minion makeNew(int target) {
		return new RiverCrocolisk(target);
	}

}