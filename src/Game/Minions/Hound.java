package Game.Minions;

public class Hound extends Minion {

	public Hound() {
		super("Hound",1,1,1);
		setRace(Race.BEAST);
		setCharge(true);
	}
	
	public Hound(Minion m) {
		super(m);
	}
	
}