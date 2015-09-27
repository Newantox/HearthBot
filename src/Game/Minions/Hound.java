package Game.Minions;

public class Hound extends Minion {

	public Hound(int target) {
		super("Hound",target,1,1,1);
		setRace(Race.BEAST);
		setCharge(true);
	}
	
	public Hound(Minion m) {
		super(m);
	}
	
}