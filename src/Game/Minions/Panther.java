package Game.Minions;

public class Panther extends Minion {

	public Panther(int target) {
		super("Panther",target,2,3,2);
		setRace(Race.BEAST);
	}
	
	public Panther(Minion m) {
		super(m);
	}
	
}