package Game.Minions;

public class NorthshireCleric extends Minion {

	public NorthshireCleric(int target) {
		super("Northshire Cleric",target,1,1,3);
		addHealEffect(new NorthshireClericHE());
	}
	
	public NorthshireCleric(Minion m) {
		super(m);
	}
	
}