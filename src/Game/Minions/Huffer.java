package Game.Minions;

public class Huffer extends Minion {

	public Huffer() {
		super("Huffer",-1,3,4,2);
		setRace(Race.BEAST);
		setCharge(true);
	}
	
	public Huffer(Minion m) {
		super(m);
	}
	
}