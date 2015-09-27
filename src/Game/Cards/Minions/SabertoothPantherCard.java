package Game.Cards.Minions;

import Game.Minions.Minion;
import Game.Minions.SabertoothPanther;

public class SabertoothPantherCard extends MinionCard {

	public SabertoothPantherCard() {
		super("Sabertooth Panther",2);
	}
	
	public Minion makeNew(int target) {
		return new SabertoothPanther(target);
	}

}