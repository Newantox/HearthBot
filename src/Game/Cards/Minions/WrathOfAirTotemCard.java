package Game.Cards.Minions;

import Game.Minions.WrathOfAirTotem;
import Game.Minions.Minion;

public class WrathOfAirTotemCard extends MinionCard {

	public WrathOfAirTotemCard() {
		super("Wrath Of Air Totem",1);
	}
	
	public Minion makeNew(int target) {
		return new WrathOfAirTotem(target);
	}

}