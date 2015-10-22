package Game.Minions;

public class OgreMagi extends Minion {

	public OgreMagi() {
		super("Ogre Magi",-1,4,4,4);
		setSpelldamage(1);
	}
	
	public OgreMagi(Minion m) {
		super(m);
	}
	
}