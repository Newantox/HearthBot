package Game.SummonEffects;

import Game.MyTurnState;
import Game.Minions.Minion;
import Game.Minions.Race;

public class StarvingBuzzardSE extends SummonEffect {
	
	public MyTurnState perform(MyTurnState oldstate, Minion source, Minion minion) {
		if (minion.getRace().equals(Race.BEAST)) {
			if (source.getMyPos()<7 && minion.getMyPos()<7) return oldstate.drawCard();
			else if (source.getMyPos()>=7 && minion.getMyPos()>=7) return oldstate.enemyDrawCard();
		}
		return oldstate;
	}
}
