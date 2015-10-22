package Game.Minions;

public class BootyBayBodyguard extends Minion {

	public BootyBayBodyguard() {
		super("Booty Bay Bodyguard",-1,5,5,4);
		setTaunt(true);
	}
	
	public BootyBayBodyguard(Minion m) {
		super(m);
	}
	
}