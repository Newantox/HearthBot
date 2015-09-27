package Game.Minions;

public class ArgentSquire extends Minion {

	public ArgentSquire(int target) {
		super("Argent Squire",target,1,1,1);
		setDivineShield(true);
	}
	
	public ArgentSquire(Minion m) {
		super(m);
	}


}