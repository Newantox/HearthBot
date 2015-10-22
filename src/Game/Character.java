package Game;

public interface Character extends PlayableCard {
	
	public String getName();
	
	public CardType getType();
	
	public MyTurnState damage(BoardState oldstate, int amount);
	
	public MyTurnState heal(BoardState oldstate, int amount);
	
	public boolean canAttack();

	public int getAtk();

	public int getMyPos();
	
	public boolean isImmune();

}
