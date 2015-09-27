package Game.Minions;

public class LordOfTheArena extends Minion {

	public LordOfTheArena(int target) {
		super("Lord Of The Arena",target,6,6,5);
		setTaunt(true);
	}
	
	public LordOfTheArena(Minion m) {
		super(m);
	}
	
}