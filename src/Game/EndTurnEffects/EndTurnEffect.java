package Game.EndTurnEffects;

import Game.MyTurnState;
import Game.Heroes.Hero;
import Game.Minions.Minion;

public abstract class EndTurnEffect {

	public abstract MyTurnState perform(MyTurnState oldstate, Hero hero, Minion source);

}
