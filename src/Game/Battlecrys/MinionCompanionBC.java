package Game.Battlecrys;

import Game.BoardState;
import Game.MyTurnState;
import Game.PlayableCard;
import Game.Minions.Minion;

public class MinionCompanionBC extends Battlecry {
	
	private Minion companion;
	
	public MinionCompanionBC(Minion companion) {
		this.companion = companion;
	}

	@Override
	public MyTurnState perform(PlayableCard minion, BoardState oldstate) {
		if (oldstate.numberOfAlliedMinions() < 7) {
			Minion newMinion = companion;
			newMinion.setMyPos(((Minion) minion).getMyPos()+1);
			return oldstate.placeMinion(newMinion);
		}
		else return oldstate;
	}
}
