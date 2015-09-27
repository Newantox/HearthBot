package Game.Heroes.HeroPowers;

import Game.BoardState;
import Search.Action;

public abstract class HeroPower implements Action {
	
	public double cost() {
		return 0.4;
	}
	
	public abstract boolean useable(BoardState oldstate);

}
