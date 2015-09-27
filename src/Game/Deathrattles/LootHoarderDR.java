package Game.Deathrattles;

import Game.BoardState;
import Game.MyTurnState;

import Game.Minions.Minion;

public class LootHoarderDR extends MinionDeathrattle {

	@Override
	public MyTurnState perform(Minion minion, BoardState oldstate) {
		if (minion.getMyPos() < 7) return oldstate.drawCard();
		else return oldstate.enemyDrawCard();
	}

}
