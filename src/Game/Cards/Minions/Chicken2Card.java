package Game.Cards.Minions;

import Game.Minions.Minion;
import Game.Minions.Chicken2;

public class Chicken2Card extends MinionCard {

	public Chicken2Card() {
		super("Chicken2",1);
	}
	
	public Minion makeNew(int target) {
		return new Chicken2(target);
	}

}