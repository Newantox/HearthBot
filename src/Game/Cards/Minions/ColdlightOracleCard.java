package Game.Cards.Minions;

import Game.Minions.ColdlightOracle;
import Game.Minions.Minion;

public class ColdlightOracleCard extends MinionCard {
	
	public ColdlightOracleCard() {
		super("Coldlight Oracle", 3);
	}

	@Override
	protected Minion makeNew(int target) {
		return new ColdlightOracle(target);
	}

}
