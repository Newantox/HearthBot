package Game.Cards.Minions;

import Game.Minions.KoboldGeomancer;
import Game.Minions.Minion;

public class KoboldGeomancerCard extends MinionCard {

	public KoboldGeomancerCard() {
		super("Kobold Geomancer",2);
	}
	
	public Minion makeNew(int target) {
		return new KoboldGeomancer(target);
	}

}