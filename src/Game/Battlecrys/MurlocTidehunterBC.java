package Game.Battlecrys;

import Game.BoardState;
import Game.Minions.Minion;
import Game.Minions.MurlocScout;
import Search.State;

public class MurlocTidehunterBC extends MinionBattlecry {

	@Override
	public State perform(Minion minion, BoardState oldstate) {
		if (minion.getMyPos()<6) return oldstate.placeMinion(new MurlocScout(minion.getMyPos()+1));
		else return oldstate;
	}

}
