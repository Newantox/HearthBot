package Game.Cards.Minions;

import Game.Minions.SilverbackPatriarch;
import Game.Minions.Minion;

public class SilverbackPatriarchCard extends MinionCard {

	public SilverbackPatriarchCard() {
		super("Silverback Patriarch",3);
	}
	
	public Minion makeNew(int target) {
		return new SilverbackPatriarch(target);
	}

}