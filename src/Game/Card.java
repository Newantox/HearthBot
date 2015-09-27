package Game;

public interface Card {
	
	public String getName();
	
	public int getCost();
	
	public MyTurnState playCard(BoardState oldstate, int target);
	
	public CardType getType();
	
}
