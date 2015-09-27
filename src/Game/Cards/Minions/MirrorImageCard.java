package Game.Cards.Minions;

import Game.Minions.MirrorImage;
import Game.Minions.Minion;

public class MirrorImageCard extends MinionCard {

	public MirrorImageCard() {
		super("Mirror Image",0);
	}
	
	public Minion makeNew(int target) {
		return new MirrorImage(target);
	}

}