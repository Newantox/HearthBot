package Game.Deathrattles;

import Game.BoardState;
import Game.Minions.Minion;
import Search.State;

public class ZombieChowDR extends Deathrattle {

	@Override
	public State perform(Minion minion, BoardState oldstate) {
		if (minion.getMyPos() < 7) return (oldstate.getEnemy()).heal(oldstate, 5);
		else return (oldstate.getHero()).heal(oldstate, 5);
	}

}
