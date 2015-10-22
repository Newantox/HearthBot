package Game.SummonEffects;

import Game.MyTurnState;
import Game.PlayableCard;
import Game.TargetsType;
import Game.Minions.Minion;

public class KnifeJugglerSE extends SummonEffect {
	
	public MyTurnState perform(MyTurnState oldstate, PlayableCard source, Minion minion) {
		if (((Minion) source).getMyPos()<7 && minion.getMyPos()<7) {
			return oldstate.damageRandomHittable(TargetsType.ENEMYCHAR, 1);
		}
		else if (((Minion) source).getMyPos()>=7 && minion.getMyPos()>=7) {
			return oldstate.damageRandomHittable(TargetsType.ALLYCHAR, 1);
		}
		else return oldstate;
	}

}
