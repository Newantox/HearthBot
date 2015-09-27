package Game.Minions;

public class DalaranMage extends Minion {

	public DalaranMage(int target) {
		super("Dalaran Mage",target,3,1,4);
		setSpelldamage(1);
	}
	
	public DalaranMage(Minion m) {
		super(m);
	}
	
}