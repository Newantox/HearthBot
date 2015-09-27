package Game.EndTurnEffects;

import Game.MyTurnState;
import Game.Heroes.Hero;

public abstract class EndTurnEffect {

	public abstract MyTurnState perform(MyTurnState oldstate, Hero hero);

}
