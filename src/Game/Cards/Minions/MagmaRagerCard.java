package Game.Cards.Minions;

import Game.Minions.MagmaRager;
import Game.Minions.Minion;

public class MagmaRagerCard extends MinionCard {

	public MagmaRagerCard() {
		super("Magma Rager",3);
	}
	
	public Minion makeNew(int target) {
		return new MagmaRager(target);
	}

}