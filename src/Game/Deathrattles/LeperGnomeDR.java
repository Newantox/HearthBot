package Game.Deathrattles;

import Game.BoardState;
import Game.MyTurnState;

import Game.Minions.Minion;

public class LeperGnomeDR extends MinionDeathrattle {

	@Override
	public MyTurnState perform(Minion minion, BoardState oldstate) {
		if (minion.getMyPos() < 7) return (oldstate.getEnemy()).damage(oldstate,2);
		else return (oldstate.getHero()).damage(oldstate,2);
	}

}
