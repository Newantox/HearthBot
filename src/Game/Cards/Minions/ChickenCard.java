package Game.Cards.Minions;

import Game.Minions.Chicken;
import Game.Minions.Minion;

public class ChickenCard extends MinionCard {

	public ChickenCard() {
		super("Chicken",0);
	}
	
	public Minion makeNew(int target) {
		return new Chicken(target);
	}

}
