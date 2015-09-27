package Game.Minions;

import Game.Auras.RaidLeaderAura;

public class RaidLeader extends Minion {

	public RaidLeader(int target) {
		super("Raid Leader",target,3,2,2);
		auras.add(new RaidLeaderAura());
	}
	
	public RaidLeader(Minion m) {
		super(m);
	}
	
}