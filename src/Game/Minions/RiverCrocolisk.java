package Game.Minions;

public class RiverCrocolisk extends Minion {

	public RiverCrocolisk(int target) {
		super("River Crocolisk",target,2,2,3);
		setRace(Race.BEAST);
	}
	
	public RiverCrocolisk(Minion m) {
		super(m);
	}
	
}