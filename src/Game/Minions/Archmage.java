package Game.Minions;

public class Archmage extends Minion {

	public Archmage(int target) {
		super("Archmage",target,6,4,7);
		setSpelldamage(1);
	}
	
	public Archmage(Minion m) {
		super(m);
	}
	
}

