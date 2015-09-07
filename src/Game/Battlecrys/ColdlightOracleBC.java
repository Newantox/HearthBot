package Game.Battlecrys;

import Game.BoardState;
import Game.Minions.Minion;
import Search.State;

public class ColdlightOracleBC extends MinionBattlecry {

	@Override
	public State perform(Minion minion, BoardState oldstate) {
		BoardState tempstate = oldstate.enemyDrawCard();
		tempstate = tempstate.enemyDrawCard();
		State tempstate2 = tempstate.drawCard();
		return tempstate2.drawCard();
	}

}
