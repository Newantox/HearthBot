package Game.SummonEffects;

import Game.MyTurnState;
import Game.Minions.Minion;

public class WarsongCommanderSE extends SummonEffect {
	
	public MyTurnState perform(MyTurnState oldstate, Minion source, Minion minion) {
		if (minion.getAtk()<=3) {
			return oldstate.changeAttributes(minion, true, minion.isDivineShield(), minion.isTaunt(), minion.isStealth(), minion.getWindfury(), minion.getSpelldamage(), minion.isFrozen());
		}
		else return oldstate;
	}
}
