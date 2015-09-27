package Game.Cards.Minions;

import Game.Minions.NorthshireCleric;
import Game.Minions.Minion;

public class NorthshireClericCard extends MinionCard {

	public NorthshireClericCard() {
		super("Northshire Cleric",1);
	}
	
	public Minion makeNew(int target) {
		return new NorthshireCleric(target);
	}

}