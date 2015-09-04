package Game.Minions;

public class Wolfrider extends Minion {
	
	public Wolfrider(int target) {
		super("Wolfrider",target,3,3,1,1);
		this.setCharge(true);;
	}
	
	public Wolfrider(Minion m) {
		super(m);
	}

}
