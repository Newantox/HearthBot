package Game.Minions;

public class ArgentSquire extends Minion {

	public ArgentSquire() {
		super("Argent Squire",-1,1,1,1);
		setDivineShield(true);
	}
	
	public ArgentSquire(Minion m) {
		super(m);
	}


}