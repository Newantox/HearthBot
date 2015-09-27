package Game.Cards.Minions;

import Game.Minions.Voidwalker;
import Game.Minions.Minion;

public class VoidwalkerCard extends MinionCard {

	public VoidwalkerCard() {
		super("Voidwalker",1);
	}
	
	public Minion makeNew(int target) {
		return new Voidwalker(target);
	}

}