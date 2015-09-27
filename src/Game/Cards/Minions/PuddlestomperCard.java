package Game.Cards.Minions;

import Game.Minions.Minion;
import Game.Minions.Puddlestomper;

public class PuddlestomperCard extends MinionCard {

	public PuddlestomperCard() {
		super("Puddlestomper", 2);
	}

	@Override
	protected Minion makeNew(int target) {
		return new Puddlestomper(target);
	}
	
}
