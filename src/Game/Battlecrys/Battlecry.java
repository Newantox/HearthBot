package Game.Battlecrys;

import Game.BoardState;
import Game.MyTurnState;
import Game.PlayableCard;

public abstract class Battlecry {
	
	public MyTurnState trigger(PlayableCard card, MyTurnState oldstate) {
		return oldstate.performBC(this,card);
	}
	
	public abstract MyTurnState perform(PlayableCard card, BoardState oldstate);

}
