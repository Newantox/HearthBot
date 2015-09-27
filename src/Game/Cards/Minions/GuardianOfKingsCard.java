package Game.Cards.Minions;

import Game.Minions.GuardianOfKings;
import Game.Minions.Minion;

public class GuardianOfKingsCard extends MinionCard {

	public GuardianOfKingsCard() {
		super("Guardian Of Kings",7);
	}
	
	public Minion makeNew(int target) {
		return new GuardianOfKings(target);
	}

}