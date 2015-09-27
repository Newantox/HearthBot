package Game.Battlecrys;

import Game.BoardState;
import Game.MyTurnState;
import Game.Minions.Minion;

public class NightbladeBC extends MinionBattlecry {

	@Override
	public MyTurnState perform(Minion minion, BoardState oldstate) {
		return (oldstate.getEnemy()).damage(oldstate,3);
	}

}

