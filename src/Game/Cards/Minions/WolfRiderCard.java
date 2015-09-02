package Game.Cards.Minions;

import Game.Minions.Minion;
import Game.Minions.WolfRider;

public class WolfRiderCard extends MinionCard {

	public WolfRiderCard() {
		super("Wolf Rider",3);
	}
	
	public Minion makeNew(int target) {
		return new WolfRider(target);
	}

}
