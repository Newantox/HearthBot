package Game.Deathrattles;

import Game.BoardState;
import Game.Minions.Minion;
import Search.State;

public class BloodmageThalnosDR extends Deathrattle {

	@Override
	public State perform(Minion minion, BoardState oldstate) {
		if (minion.getMyPos() < 7) return oldstate.drawCard();
		else return oldstate.enemyDrawCard();
	}

}
