package Game.Cards.Minions;

import Game.Minions.Misha;
import Game.Minions.Minion;

public class MishaCard extends MinionCard {

	public MishaCard() {
		super("Misha",3);
	}
	
	public Minion makeNew(int target) {
		return new Misha(target);
	}

}