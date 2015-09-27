package Game.Cards.Minions;

import Game.Minions.StoneclawTotem;
import Game.Minions.Minion;

public class StoneclawTotemCard extends MinionCard {

	public StoneclawTotemCard() {
		super("Stoneclaw Totem",1);
	}
	
	public Minion makeNew(int target) {
		return new StoneclawTotem(target);
	}

}