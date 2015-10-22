package Game.Minions;

import Game.Auras.RaidLeaderAura;

public class RaidLeader extends Minion {

	public RaidLeader() {
		super("Raid Leader",-1,3,2,2);
		auras.add(new RaidLeaderAura());
	}
	
	public RaidLeader(Minion m) {
		super(m);
	}
	
}