package Game.Battlecrys;

import Game.BoardState;
import Game.MyTurnState;
import Game.PlayableCard;

public class ColdlightOracleBC extends Battlecry {

	@Override
	public MyTurnState perform(PlayableCard minion, BoardState oldstate) {
		MyTurnState tempstate = oldstate.enemyDrawCard();
		tempstate = tempstate.enemyDrawCard();
		tempstate = tempstate.drawCard();
		return tempstate.drawCard();
	}

}
