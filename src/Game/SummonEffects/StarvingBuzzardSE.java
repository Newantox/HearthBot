package Game.SummonEffects;

import Game.MyTurnState;
import Game.PlayableCard;
import Game.TargetsType;
import Game.Minions.Minion;
import Game.Minions.Race;

public class StarvingBuzzardSE extends SummonEffect {
	
	public MyTurnState perform(MyTurnState oldstate, PlayableCard source, Minion minion, TargetsType side) {
		if (minion.getRace().equals(Race.BEAST)) {
			if (side.equals(TargetsType.ALLYCHAR)) return oldstate.drawCard();
			else if (side.equals(TargetsType.ENEMYCHAR)) return oldstate.enemyDrawCard();
		}
		return oldstate;
	}

	@Override
	public TargetsType getEffectRange() {
		return TargetsType.ALLYMINIONS;
	}
}
