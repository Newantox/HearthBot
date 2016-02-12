package Game.Minions;

public class KoboldGeomancer extends Minion {

	public KoboldGeomancer() {
		super("Kobold Geomancer",2,2,2);
		setSpelldamage(1);
	}
	
	public KoboldGeomancer(Minion m) {
		super(m);
	}
	
}