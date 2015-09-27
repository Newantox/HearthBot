package Game.Battlecrys;

import Game.BoardState;
import Game.Minions.Minion;
import Search.State;

public class DarkscaleHealerBC extends MinionBattlecry {

	@Override
	//public MyTurnState perform(Minion minion, BoardState oldstate) {
		return oldstate.drawCard();
	}

}

