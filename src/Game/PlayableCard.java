package Game;

public interface PlayableCard extends Card {
	
	public int getCost();
	
	public MyTurnState playCard(BoardState oldstate, Character target);

}
