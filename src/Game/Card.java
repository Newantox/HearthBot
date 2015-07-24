package Game;

import Search.State;

public interface Card {
	
	public String getName();
	
	public int getCost();
	
	public State playCard(BoardState oldstate, int target, int pos);
	
}
