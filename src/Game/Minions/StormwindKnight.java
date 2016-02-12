package Game.Minions;

public class StormwindKnight extends Minion {

	public StormwindKnight() {
		super("Stormwind Knight",4,2,5);
		setCharge(true);
	}
	
	public StormwindKnight(Minion m) {
		super(m);
	}
	
}