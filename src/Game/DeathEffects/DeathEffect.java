package Game.DeathEffects;

import Game.MyTurnState;
import Game.TargetsType;
import Game.Minions.Minion;

public abstract class DeathEffect {

	public abstract MyTurnState perform(MyTurnState oldstate, Minion source, Minion minion);
	
	public abstract TargetsType getEffectRange();
}
