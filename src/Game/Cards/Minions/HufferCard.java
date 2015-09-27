package Game.Cards.Minions;

import Game.Minions.Huffer;
import Game.Minions.Minion;

public class HufferCard extends MinionCard {

	public HufferCard() {
		super("Huffer",3);
	}
	
	public Minion makeNew(int target) {
		return new Huffer(target);
	}

}