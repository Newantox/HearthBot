package Game.Cards.Minions;

import Game.Minions.RazorfenHunter;
import Game.Minions.Minion;

public class RazorfenHunterCard extends MinionCard {

	public RazorfenHunterCard() {
		super("Razorfen Hunter",3);
	}
	
	public Minion makeNew(int target) {
		return new RazorfenHunter(target);
	}

}