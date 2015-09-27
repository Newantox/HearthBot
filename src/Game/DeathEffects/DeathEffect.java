package Game.DeathEffects;

import Game.MyTurnState;
import Game.Minions.Minion;

public abstract class DeathEffect {

	public abstract MyTurnState perform(MyTurnState oldstate, Minion source, Minion minion);
}
