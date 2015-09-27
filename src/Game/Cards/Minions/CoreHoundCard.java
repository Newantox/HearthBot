package Game.Cards.Minions;

import Game.Minions.CoreHound;
import Game.Minions.Minion;

public class CoreHoundCard extends MinionCard {

	public CoreHoundCard() {
		super("Core Hound",7);
	}
	
	public Minion makeNew(int target) {
		return new CoreHound(target);
	}

}