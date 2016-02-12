package Game.Deathrattles;

import Game.BoardState;
import Game.MyTurnState;
import Game.PlayableCard;
import Game.TargetsType;

public abstract class Deathrattle {
	
	public MyTurnState trigger(MyTurnState oldstate, PlayableCard card , TargetsType side) {
		return oldstate.performDR(this,card,side);
	}
	
	public abstract MyTurnState perform(BoardState oldstate, PlayableCard card, TargetsType side);
	
}
