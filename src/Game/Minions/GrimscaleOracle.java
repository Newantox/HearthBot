package Game.Minions;

import Game.Auras.GrimscaleOracleAura;

public class GrimscaleOracle extends Minion {
	
	public GrimscaleOracle(int target) {
		super("Grimscale Oracle",target,1,1,1);
		setRace(Race.MURLOC);
		auras.add(new GrimscaleOracleAura());
	}
	
	public GrimscaleOracle(Minion m) {
		super(m);
	}

}
