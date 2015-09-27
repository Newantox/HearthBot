package Game.Cards.Minions;

import Game.Minions.Minion;
import Game.Minions.Webspinner;

public class WebspinnerCard extends MinionCard {

	public WebspinnerCard() {
		super("Webspinner",1);
	}
	
	public Minion makeNew(int target) {
		return new Webspinner(target);
	}

}