package Game.SummonEffects;

import Game.MyTurnState;
import Game.PlayableCard;
import Game.Minions.Minion;

public abstract class SummonEffect {

	public abstract MyTurnState perform(MyTurnState oldstate, PlayableCard source , Minion summonedMinion);

}
