package Game.Cards.Minions;

import Game.Minions.BoulderfistOgre;
import Game.Minions.Minion;

public class BoulderfistOgreCard extends MinionCard {

	public BoulderfistOgreCard() {
		super("BoulderfistOgre",6);
	}
	
	public Minion makeNew(int target) {
		return new BoulderfistOgre(target);
	}

}