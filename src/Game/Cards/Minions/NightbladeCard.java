package Game.Cards.Minions;

import Game.Minions.Nightblade;
import Game.Minions.Minion;

public class NightbladeCard extends MinionCard {

	public NightbladeCard() {
		super("Nightblade",5);
	}
	
	public Minion makeNew(int target) {
		return new Nightblade(target);
	}

}