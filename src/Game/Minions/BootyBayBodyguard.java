package Game.Minions;

public class BootyBayBodyguard extends Minion {

	public BootyBayBodyguard(int target) {
		super("Booty Bay Bodyguard",target,5,5,4);
		setTaunt(true);
	}
	
	public BootyBayBodyguard(Minion m) {
		super(m);
	}
	
}