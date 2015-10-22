package Game.Battlecrys;

import java.util.ArrayList;

import Game.BoardState;
import Game.MyTurnState;
import Game.PlayableCard;
import Game.TargetsType;
import Game.Minions.Minion;

public class DarkscaleHealerBC extends Battlecry {

	@Override
	public MyTurnState perform(PlayableCard card, BoardState oldstate) {
		return oldstate.simultaneousHeal(TargetsType.ALLYCHAR, 2, new ArrayList<Minion>());
	}

}

