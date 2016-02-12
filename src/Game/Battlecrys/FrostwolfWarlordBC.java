package Game.Battlecrys;

import Game.BoardState;
import Game.MyTurnState;
import Game.PlayableCard;
import Game.Buffs.AdditiveBuff;
import Game.Minions.Minion;

public class FrostwolfWarlordBC extends Battlecry {

	@Override
	public MyTurnState perform(PlayableCard minion, BoardState oldstate) {
		return oldstate.applyBuff(((Minion) minion).getId(),((Minion) minion).getName(), new AdditiveBuff(-1, oldstate.numberOfAlliedMinions(), oldstate.numberOfAlliedMinions(), 0));
	}

}
