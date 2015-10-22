package Game.Minions;

import Game.SummonEffects.WarsongCommanderSE;

public class WarsongCommander extends Minion {

	public WarsongCommander() {
		super("Warsong Commander",-1,3,2,3);
		addSummonEffect(new WarsongCommanderSE());
	}
	
	public WarsongCommander(Minion m) {
		super(m);
	}
	
}