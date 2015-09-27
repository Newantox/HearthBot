package Game.Deathrattles;

import Game.BoardState;
import Game.MyTurnState;
import Game.Minions.Minion;

public class ZombieChowDR extends MinionDeathrattle {

	@Override
	public MyTurnState perform(Minion minion, BoardState oldstate) {
		if (minion.getMyPos() < 7) return (oldstate.getEnemy()).heal(oldstate, 5);
		else return (oldstate.getHero()).heal(oldstate, 5);
	}

}
