package Game.Minions;

public class RiverCrocolisk extends Minion {

	public RiverCrocolisk() {
		super("River Crocolisk",-1,2,2,3);
		setRace(Race.BEAST);
	}
	
	public RiverCrocolisk(Minion m) {
		super(m);
	}
	
}