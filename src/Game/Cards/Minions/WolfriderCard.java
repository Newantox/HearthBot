package Game.Cards.Minions;

import Game.Minions.Minion;
import Game.Minions.Wolfrider;

public class WolfriderCard extends MinionCard {

	public WolfriderCard() {
		super("Wolfrider",3);
	}
	
	public Minion makeNew(int target) {
		return new Wolfrider(target);
	}

}
