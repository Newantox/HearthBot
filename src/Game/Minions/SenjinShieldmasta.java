package Game.Minions;

public class SenjinShieldmasta extends Minion {

	public SenjinShieldmasta(int target) {
		super("Senjin Shieldmasta",target,4,3,5);
		setTaunt(true);
	}
	
	public SenjinShieldmasta(Minion m) {
		super(m);
	}
	
}