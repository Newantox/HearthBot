package Game.Cards.Minions;

import Game.Minions.WarsongCommander;
import Game.Minions.Minion;

public class WarsongCommanderCard extends MinionCard {

	public WarsongCommanderCard() {
		super("Warsong Commander",3);
	}
	
	public Minion makeNew(int target) {
		return new WarsongCommander(target);
	}

}