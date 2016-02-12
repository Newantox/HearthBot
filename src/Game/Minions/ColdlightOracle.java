package Game.Minions;

import Game.Battlecrys.ColdlightOracleBC;

public class ColdlightOracle extends Minion {
	
	public ColdlightOracle() {
		super("Coldlight Oracle",3,2,2);
		setRace(Race.MURLOC);
		battlecrys.add(new ColdlightOracleBC());
	}
	
	public ColdlightOracle(Minion m) {
		super(m);
	}

}
