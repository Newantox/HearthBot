package Game.Cards.Minions;

import Game.Minions.Minion;
import Game.Minions.Murloc;

public class MurlocCard extends MinionCard {
	
	public MurlocCard() {
		super("Murloc", 1);
	}

	@Override
	protected Minion makeNew(int target) {
		return new Murloc(target);
	}

}
