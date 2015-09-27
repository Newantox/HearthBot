package Game.Cards.Minions;

import Game.Minions.Minion;
import Game.Minions.Frog;

public class FrogCard extends MinionCard {

	public FrogCard() {
		super("Frog",0);
	}
	
	public Minion makeNew(int target) {
		return new Frog(target);
	}

}