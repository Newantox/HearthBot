package Game.Cards.Minions;

import Game.Minions.KorkronElite;
import Game.Minions.Minion;

public class KorkronEliteCard extends MinionCard {

	public KorkronEliteCard() {
		super("Korkron Elite",4);
	}
	
	public Minion makeNew(int target) {
		return new KorkronElite(target);
	}

}