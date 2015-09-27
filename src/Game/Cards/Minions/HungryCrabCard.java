package Game.Cards.Minions;

import Game.Minions.Minion;
import Game.Minions.HungryCrab;

public class HungryCrabCard extends MinionCard {

	public HungryCrabCard() {
		super("Hungry Crab",1);
	}
	
	public Minion makeNew(int target) {
		return new HungryCrab(target);
	}

}