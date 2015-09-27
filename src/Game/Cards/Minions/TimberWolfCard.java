package Game.Cards.Minions;

import Game.Minions.Minion;
import Game.Minions.TimberWolf;

public class TimberWolfCard extends MinionCard {

	public TimberWolfCard() {
		super("Timber Wolf",1);
	}
	
	public Minion makeNew(int target) {
		return new TimberWolf(target);
	}

}