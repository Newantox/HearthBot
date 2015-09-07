package Game.Minions;

import Game.Battlecrys.ColdlightOracleBC;

public class ColdlightOracle extends Minion {
	
	public ColdlightOracle(int target) {
		super("Coldlight Oracle",target,3,2,2,2);
		setRace(Race.MURLOC);
		battlecrys.add(new ColdlightOracleBC());
	}
	
	public ColdlightOracle(Minion m) {
		super(m);
	}

}
