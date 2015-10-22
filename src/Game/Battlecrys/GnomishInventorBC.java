package Game.Battlecrys;

import Game.BoardState;
import Game.MyTurnState;
import Game.PlayableCard;

public class GnomishInventorBC extends Battlecry {

	@Override
	public MyTurnState perform(PlayableCard minion, BoardState oldstate) {
		return oldstate.drawCard();
	}

}

