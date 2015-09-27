package Game.Battlecrys;

import Game.BoardState;
import Game.MyTurnState;
import Game.Minions.Boar;
import Game.Minions.Minion;

public class RazorfenHunterBC extends MinionBattlecry {

	@Override
	public MyTurnState perform(Minion minion, BoardState oldstate) {
		if (oldstate.numberOfAlliedMinions() < 7) return oldstate.placeMinion(new Boar(minion.getMyPos()+1));
		else return oldstate;
	}

}
