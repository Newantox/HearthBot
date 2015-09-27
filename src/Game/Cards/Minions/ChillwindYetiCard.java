package Game.Cards.Minions;

import Game.Minions.ChillwindYeti;
import Game.Minions.Minion;

public class ChillwindYetiCard extends MinionCard {

	public ChillwindYetiCard() {
		super("Chillwind Yeti",4);
	}
	
	public Minion makeNew(int target) {
		return new ChillwindYeti(target);
	}

}