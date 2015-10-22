package Game.SummonEffects;

import Game.MyTurnState;
import Game.PlayableCard;
import Game.Minions.Minion;
import Game.Minions.Race;

public class StarvingBuzzardSE extends SummonEffect {
	
	public MyTurnState perform(MyTurnState oldstate, PlayableCard source, Minion minion) {
		if (minion.getRace().equals(Race.BEAST)) {
			if (((Minion) source).getMyPos()<7 && minion.getMyPos()<7) return oldstate.drawCard();
			else if (((Minion) source).getMyPos()>=7 && minion.getMyPos()>=7) return oldstate.enemyDrawCard();
		}
		return oldstate;
	}
}
