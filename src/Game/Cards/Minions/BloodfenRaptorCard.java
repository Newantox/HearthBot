package Game.Cards.Minions;

import Game.Minions.BloodfenRaptor;
import Game.Minions.Minion;

public class BloodfenRaptorCard extends MinionCard {

	public BloodfenRaptorCard() {
		super("Bloodfen Raptor",2);
	}
	
	public Minion makeNew(int target) {
		return new BloodfenRaptor(target);
	}

}