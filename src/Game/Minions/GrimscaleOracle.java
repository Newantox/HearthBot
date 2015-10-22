package Game.Minions;

import Game.Auras.GrimscaleOracleAura;

public class GrimscaleOracle extends Minion {
	
	public GrimscaleOracle() {
		super("Grimscale Oracle",-1,1,1,1);
		setRace(Race.MURLOC);
		auras.add(new GrimscaleOracleAura());
	}
	
	public GrimscaleOracle(Minion m) {
		super(m);
	}

}
