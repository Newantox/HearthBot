package Game.Deathrattles;

import Game.BoardState;
import Game.Minions.Minion;
import Search.State;

public class LeperGnomeDR extends MinionDeathrattle {

	@Override
	public State perform(Minion minion, BoardState oldstate) {
		if (minion.getMyPos() < 7) return (oldstate.getEnemy()).damage(oldstate,2);
		else return (oldstate.getHero()).damage(oldstate,2);
	}

}
