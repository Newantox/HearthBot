package Game;

public interface PlayableCard extends Card {
	
	public int getCost();
	
	public MyTurnState playCard(BoardState oldstate, Character target);
	
	public void playPrint(Character target);
	
	public String playOutput(Character target);

}
