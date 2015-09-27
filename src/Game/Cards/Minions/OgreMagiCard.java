package Game.Cards.Minions;

import Game.Minions.OgreMagi;
import Game.Minions.Minion;

public class OgreMagiCard extends MinionCard {

	public OgreMagiCard() {
		super("Ogre Magi",4);
	}
	
	public Minion makeNew(int target) {
		return new OgreMagi(target);
	}

}