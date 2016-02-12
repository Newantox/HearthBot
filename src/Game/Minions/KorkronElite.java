package Game.Minions;

public class KorkronElite extends Minion {

	public KorkronElite() {
		super("Korkron Elite",4,4,3);
		setCharge(true);
	}
	
	public KorkronElite(Minion m) {
		super(m);
	}
	
}