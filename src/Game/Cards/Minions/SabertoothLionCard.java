package Game.Cards.Minions;

import Game.Minions.Minion;
import Game.Minions.SabertoothLion;

public class SabertoothLionCard extends MinionCard {

	public SabertoothLionCard() {
		super("Sabertooth Lion",2);
	}
	
	public Minion makeNew(int target) {
		return new SabertoothLion(target);
	}

}