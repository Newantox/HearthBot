package Game.Minions;

public class Misha extends Minion {

	public Misha(int target) {
		super("Misha",target,3,4,4);
		setRace(Race.BEAST);
		setTaunt(true);
	}
	
	public Misha(Minion m) {
		super(m);
	}
	
}