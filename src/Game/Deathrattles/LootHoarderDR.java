package Game.Deathrattles;

import Game.BoardState;
import Game.MyTurnState;
import Game.PlayableCard;
import Game.TargetsType;

public class LootHoarderDR extends Deathrattle {

	@Override
	public MyTurnState perform(BoardState oldstate, PlayableCard minion, TargetsType side) {
		if (side.equals(TargetsType.ALLYCHAR)) return oldstate.drawCard();
		else return oldstate.enemyDrawCard();
	}

}
