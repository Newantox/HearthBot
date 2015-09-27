package Game.Cards.Minions;

import Game.Minions.Minion;
import Game.Minions.YoungDragonhawk;

public class YoungDragonhawkCard extends MinionCard {

	public YoungDragonhawkCard() {
		super("Young DragonhawkCard",1);
	}
	
	public Minion makeNew(int target) {
		return new YoungDragonhawk(target);
	}

}