package Game.Deathrattles;

import Game.BoardState;
import Game.MyTurnState;
import Game.PlayableCard;
import Game.Minions.Minion;

public class LootHoarderDR extends Deathrattle {

	@Override
	public MyTurnState perform(PlayableCard minion, BoardState oldstate) {
		if (((Minion) minion).getMyPos() < 7) return oldstate.drawCard();
		else return oldstate.enemyDrawCard();
	}

}
