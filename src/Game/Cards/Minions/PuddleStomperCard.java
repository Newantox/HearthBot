package Game.Cards.Minions;

import Game.Minions.Minion;
import Game.Minions.PuddleStomper;

public class PuddleStomperCard extends MinionCard {

	public PuddleStomperCard(String name, int cost) {
		super("Puddle Stomper", 2);
	}

	@Override
	protected Minion makeNew(int target) {
		return new PuddleStomper(target);
	}
	
}
