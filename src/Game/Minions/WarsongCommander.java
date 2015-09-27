package Game.Minions;

import Game.SummonEffects.WarsongCommanderSE;

public class WarsongCommander extends Minion {

	public WarsongCommander(int target) {
		super("Warsong Commander",target,3,2,3);
		addSummonEffect(new WarsongCommanderSE());
	}
	
	public WarsongCommander(Minion m) {
		super(m);
	}
	
}