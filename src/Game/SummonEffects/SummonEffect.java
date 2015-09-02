package Game.SummonEffects;

import Game.Minions.Minion;
import Search.State;

public abstract class SummonEffect {

	public abstract State perform(State oldstate, Minion minion);
}
