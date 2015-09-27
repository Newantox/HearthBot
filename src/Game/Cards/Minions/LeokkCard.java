package Game.Cards.Minions;

import Game.Minions.Leokk;
import Game.Minions.Minion;

public class LeokkCard extends MinionCard {

	public LeokkCard() {
		super("Leokk",3);
	}
	
	public Minion makeNew(int target) {
		return new Leokk(target);
	}

}