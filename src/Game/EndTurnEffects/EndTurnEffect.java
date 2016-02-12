package Game.EndTurnEffects;

import Game.MyTurnState;
import Game.TargetsType;
import Game.Minions.Minion;

public abstract class EndTurnEffect {

	public abstract MyTurnState perform(MyTurnState oldstate, TargetsType side,  Minion source);

}
