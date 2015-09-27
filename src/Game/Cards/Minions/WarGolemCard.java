package Game.Cards.Minions;

import Game.Minions.Minion;
import Game.Minions.WarGolem;

public class WarGolemCard extends MinionCard {

	public WarGolemCard() {
		super("War Golem",7);
	}
	
	public Minion makeNew(int target) {
		return new WarGolem(target);
	}

}