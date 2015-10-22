package Game.Minions;

public class Panther extends Minion {

	public Panther() {
		super("Panther",-1,2,3,2);
		setRace(Race.BEAST);
	}
	
	public Panther(Minion m) {
		super(m);
	}
	
}