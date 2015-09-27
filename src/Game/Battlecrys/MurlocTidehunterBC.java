package Game.Battlecrys;

import Game.BoardState;
import Game.MyTurnState;
import Game.Minions.Minion;
import Game.Minions.MurlocScout;

public class MurlocTidehunterBC extends MinionBattlecry {

	@Override
	public MyTurnState perform(Minion minion, BoardState oldstate) {
		if (oldstate.numberOfAlliedMinions() < 7) return oldstate.placeMinion(new MurlocScout(minion.getMyPos()+1));
		else return oldstate;
	}

}
