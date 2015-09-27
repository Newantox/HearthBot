package Game.Cards.Minions;

import Game.Minions.GoldshireFootman;
import Game.Minions.Minion;

public class GoldshireFootmanCard extends MinionCard {

	public GoldshireFootmanCard() {
		super("Goldshire Footman",1);
	}
	
	public Minion makeNew(int target) {
		return new GoldshireFootman(target);
	}

}