package Game.SummonEffects;

import Game.MyTurnState;
import Game.TargetsType;
import Game.Minions.Minion;

public class KnifeJugglerSE extends SummonEffect {
	
	public MyTurnState perform(MyTurnState oldstate, Minion source, Minion minion) {
		if (source.getMyPos()<7 && minion.getMyPos()<7) {
			return oldstate.damageRandomHittable(TargetsType.ENEMYCHAR, 1);
		}
		else if (source.getMyPos()>=7 && minion.getMyPos()>=7) {
			return oldstate.damageRandomHittable(TargetsType.ALLYCHAR, 1);
		}
		else return oldstate;
	}

}
