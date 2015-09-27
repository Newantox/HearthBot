package Game.Cards.Minions;

import Game.Minions.RaidLeader;
import Game.Minions.Minion;

public class RaidLeaderCard extends MinionCard {

	public RaidLeaderCard() {
		super("RaidLeader",3);
	}
	
	public Minion makeNew(int target) {
		return new RaidLeader(target);
	}

}