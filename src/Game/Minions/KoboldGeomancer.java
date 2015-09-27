package Game.Minions;

public class KoboldGeomancer extends Minion {

	public KoboldGeomancer(int target) {
		super("Kobold Geomancer",target,2,2,2);
		setSpelldamage(1);
	}
	
	public KoboldGeomancer(Minion m) {
		super(m);
	}
	
}