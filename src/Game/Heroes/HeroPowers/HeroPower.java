package Game.Heroes.HeroPowers;

import Game.BoardState;
import Search.Action;

public interface HeroPower extends Action {

	int getCost();
	
	boolean useable(BoardState oldstate);

}
