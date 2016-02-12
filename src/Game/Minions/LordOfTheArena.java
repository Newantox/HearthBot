package Game.Minions;

public class LordOfTheArena extends Minion {

	public LordOfTheArena() {
		super("Lord Of The Arena",6,6,5);
		setTaunt(true);
	}
	
	public LordOfTheArena(Minion m) {
		super(m);
	}
	
}