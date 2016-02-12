package Game.Minions;

public class SenjinShieldmasta extends Minion {

	public SenjinShieldmasta() {
		super("Senjin Shieldmasta",4,3,5);
		setTaunt(true);
	}
	
	public SenjinShieldmasta(Minion m) {
		super(m);
	}
	
}