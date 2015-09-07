package Game.Cards.Minions;

import Game.Minions.MurlocRaider;
import Game.Minions.Minion;

public class MurlocRaiderCard extends MinionCard {
	
	public MurlocRaiderCard(String name, int cost) {
		super("Murloc Raider", 1);
	}

	@Override
	protected Minion makeNew(int target) {
		return new MurlocRaider(target);
	}

}
