package Game.Cards.Minions;

import Game.Minions.OasisSnapjaw;
import Game.Minions.Minion;

public class OasisSnapjawCard extends MinionCard {

	public OasisSnapjawCard() {
		super("Oasis Snapjaw",4);
	}
	
	public Minion makeNew(int target) {
		return new OasisSnapjaw(target);
	}

}
