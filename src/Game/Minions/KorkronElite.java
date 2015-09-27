package Game.Minions;

public class KorkronElite extends Minion {

	public KorkronElite(int target) {
		super("Korkron Elite",target,4,4,3);
		setCharge(true);
	}
	
	public KorkronElite(Minion m) {
		super(m);
	}
	
}