package Game.Battlecrys;

import Game.BoardState;
import Game.MyTurnState;
import Game.Minions.MechanicalDragonling;
import Game.Minions.Minion;

public class DragonlingMechanicBC extends MinionBattlecry {

	@Override
	public MyTurnState perform(Minion minion, BoardState oldstate) {
		if (oldstate.numberOfAlliedMinions() < 7) return oldstate.placeMinion(new MechanicalDragonling(minion.getMyPos()+1));
		else return oldstate;
	}

}
