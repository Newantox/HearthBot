package Game.Minions;

public class StormwindKnight extends Minion {

	public StormwindKnight(int target) {
		super("Stormwind Knight",target,4,2,5);
		setCharge(true);
	}
	
	public StormwindKnight(Minion m) {
		super(m);
	}
	
}