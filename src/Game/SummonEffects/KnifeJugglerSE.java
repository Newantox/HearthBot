package Game.SummonEffects;

import Game.MyTurnState;
import Game.PlayableCard;
import Game.TargetsType;
import Game.Minions.Minion;

public class KnifeJugglerSE extends SummonEffect {
	
	public MyTurnState perform(MyTurnState oldstate, PlayableCard source, Minion minion, TargetsType side) {
		if (side.equals(TargetsType.ALLYCHAR)) return oldstate.damageRandomHittable(TargetsType.ENEMYCHAR, 1);
		else return oldstate.damageRandomHittable(TargetsType.ALLYCHAR, 1);
	}

	@Override
	public TargetsType getEffectRange() {
		return TargetsType.ALLYMINIONS;
	}

}
