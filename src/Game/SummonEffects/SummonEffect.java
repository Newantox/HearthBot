package Game.SummonEffects;

import Game.MyTurnState;
import Game.Minions.Minion;

public abstract class SummonEffect {

	public abstract MyTurnState perform(MyTurnState oldstate, Minion minion, Minion summonedMinion);

}
