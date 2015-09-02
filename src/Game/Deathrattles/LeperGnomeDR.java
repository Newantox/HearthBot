package Game.Deathrattles;

import Game.BoardState;
import Game.Minions.Minion;
import Search.State;

public class LeperGnomeDR extends Deathrattle {

	@Override
	public State perform(Minion minion, BoardState oldstate) {
		System.out.println("lepergnomedr");
		if (minion.getMyPos() < 7) return (oldstate.getEnemy()).damage(oldstate,2);
		else return (oldstate.getHero()).damage(oldstate,2);
	}

}
