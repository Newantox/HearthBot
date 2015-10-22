package Game.Deathrattles;

import Game.BoardState;
import Game.MyTurnState;
import Game.PlayableCard;

public abstract class Deathrattle {
	
	public MyTurnState trigger(PlayableCard card , MyTurnState oldstate) {
		return oldstate.performDR(this,card);
	}
	
	public abstract MyTurnState perform(PlayableCard card, BoardState oldstate);
	
}
