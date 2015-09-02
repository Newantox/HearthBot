package Game.Minions;

public class WolfRider extends Minion {
	
	public WolfRider(int target) {
		super("Wolf Rider",target,3,3,1,1);
		this.setCharge(true);;
	}
	
	public WolfRider(Minion m) {
		super(m);
	}

}
