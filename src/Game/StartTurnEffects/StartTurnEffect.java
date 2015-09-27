package Game.StartTurnEffects;

import Game.MyTurnState;
import Game.Heroes.Hero;

public abstract class StartTurnEffect {

	public abstract MyTurnState perform(MyTurnState oldstate, Hero hero);

}
