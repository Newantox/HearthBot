package Game.Cards.Minions;

import Game.Minions.Minion;
import Game.Minions.Squirrel;

public class SquirrelCard extends MinionCard {

	public SquirrelCard() {
		super("Squirrel",1);
	}
	
	public Minion makeNew(int target) {
		return new Squirrel(target);
	}

}