package Game.Cards.Minions;

import Game.Minions.OldMurkEye;
import Game.Minions.Minion;

public class OldMurkEyeCard extends MinionCard {
	
	public OldMurkEyeCard() {
		super("Old Murk-Eye", 4);
	}

	@Override
	protected Minion makeNew(int target) {
		return new OldMurkEye(target);
	}

}
