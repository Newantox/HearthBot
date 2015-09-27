package Game.SummonEffects;

import Game.MyTurnState;
import Game.Minions.Minion;
import Game.Minions.Race;

public class MurlocTidecallerSE extends SummonEffect {
	
	public MyTurnState perform(MyTurnState oldstate, Minion source, Minion minion) {
		if (minion.getRace().equals(Race.MURLOC)) return oldstate.changeAtkHP(source,1,0);
		else return oldstate;
	}
}
