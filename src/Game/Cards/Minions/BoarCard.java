package Game.Cards.Minions;

import Game.Minions.Minion;
import Game.Minions.Boar;

public class BoarCard extends MinionCard {

	public BoarCard() {
		super("Boar",1);
	}
	
	public Minion makeNew(int target) {
		return new Boar(target);
	}

}