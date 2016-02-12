package Game.Minions;

public class CoreHound extends Minion {

	public CoreHound() {
		super("Core Hound",7,9,5);
		setRace(Race.BEAST);
	}
	
	public CoreHound(Minion m) {
		super(m);
	}
	
}

