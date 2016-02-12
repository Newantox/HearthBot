package Game.Minions;

public class DalaranMage extends Minion {

	public DalaranMage() {
		super("Dalaran Mage",3,1,4);
		setSpelldamage(1);
	}
	
	public DalaranMage(Minion m) {
		super(m);
	}
	
}