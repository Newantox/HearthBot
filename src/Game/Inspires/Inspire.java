package Game.Inspires;

import Game.BoardState;
import Game.MyTurnState;
import Game.PlayableCard;

public abstract class Inspire {
	
	public MyTurnState trigger(PlayableCard card, MyTurnState oldstate) {
		return oldstate.performInspire(this,card);
	}
	
	public abstract MyTurnState perform(PlayableCard card, BoardState oldstate);
	
}
