package Game.Cards.Minions;

import Game.Minions.GrimscaleOracle;
import Game.Minions.Minion;

public class GrimscaleOracleCard extends MinionCard {
	
	public GrimscaleOracleCard() {
		super("Grimscale Oracle", 1);
	}

	@Override
	protected Minion makeNew(int target) {
		return new GrimscaleOracle(target);
	}


}
