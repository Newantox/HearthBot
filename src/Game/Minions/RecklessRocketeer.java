package Game.Minions;

public class RecklessRocketeer extends Minion {

	public RecklessRocketeer(int target) {
		super("Reckless Rocketeer",target,6,5,2);
		setCharge(true);
	}
	
	public RecklessRocketeer(Minion m) {
		super(m);
	}
	
}