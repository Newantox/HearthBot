package Game.Cards.Minions;

import Game.Minions.Minion;
import Game.Minions.Sheep;

public class SheepCard extends MinionCard {

	public SheepCard() {
		super("Sheep",0);
	}
	
	public Minion makeNew(int target) {
		return new Sheep(target);
	}

}