package Game.Cards.Minions;

import Game.Minions.RecklessRocketeer;
import Game.Minions.Minion;

public class RecklessRocketeerCard extends MinionCard {

	public RecklessRocketeerCard() {
		super("Reckless Rocketeer",6);
	}
	
	public Minion makeNew(int target) {
		return new RecklessRocketeer(target);
	}

}