package Game.Battlecrys;

import Game.BoardState;
import Game.MyTurnState;
import Game.Minions.Minion;

public class ColdlightOracleBC extends MinionBattlecry {

	@Override
	public MyTurnState perform(Minion minion, BoardState oldstate) {
		MyTurnState tempstate = oldstate.enemyDrawCard();
		tempstate = tempstate.enemyDrawCard();
		tempstate = tempstate.drawCard();
		return tempstate.drawCard();
	}

}
