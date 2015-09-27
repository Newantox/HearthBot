package Game.Cards.Minions;

import Game.Minions.MurlocWarleader;
import Game.Minions.Minion;

public class MurlocWarleaderCard extends MinionCard {

	public MurlocWarleaderCard() {
		super("Murloc Warleader", 3);
	}

	@Override
	protected Minion makeNew(int target) {
		return new MurlocWarleader(target);
	}

}
