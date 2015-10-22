package Game.Minions;

public class RecklessRocketeer extends Minion {

	public RecklessRocketeer() {
		super("Reckless Rocketeer",-1,6,5,2);
		setCharge(true);
	}
	
	public RecklessRocketeer(Minion m) {
		super(m);
	}
	
}