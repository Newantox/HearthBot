package Game.Cards.Minions;

import Game.Minions.Minion;
import Game.Minions.Murloc;

public class MurlocCard extends MinionCard {
	
	public MurlocCard(String name, int cost) {
		super("Murloc", 1);
	}

	@Override
	protected Minion makeNew(int target) {
		return new Murloc(target);
	}

}
