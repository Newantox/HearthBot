package Game.Minions;

public class Archmage extends Minion {

	public Archmage() {
		super("Archmage",6,4,7);
		setSpelldamage(1);
	}
	
	public Archmage(Minion m) {
		super(m);
	}
	
}

