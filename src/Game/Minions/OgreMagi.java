package Game.Minions;

public class OgreMagi extends Minion {

	public OgreMagi(int target) {
		super("Ogre Magi",target,4,4,4);
		setSpelldamage(1);
	}
	
	public OgreMagi(Minion m) {
		super(m);
	}
	
}