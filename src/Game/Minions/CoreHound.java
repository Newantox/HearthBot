package Game.Minions;

public class CoreHound extends Minion {

	public CoreHound(int target) {
		super("Core Hound",target,7,9,5);
		setRace(Race.BEAST);
	}
	
	public CoreHound(Minion m) {
		super(m);
	}
	
}

