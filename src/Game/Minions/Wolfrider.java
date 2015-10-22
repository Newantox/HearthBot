package Game.Minions;

public class Wolfrider extends Minion {
	
	public Wolfrider() {
		super("Wolfrider",-1,3,3,1);
		this.setCharge(true);;
	}
	
	public Wolfrider(Minion m) {
		super(m);
	}

}
