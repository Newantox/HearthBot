package Game.Minions;

public class NorthshireCleric extends Minion {

	public NorthshireCleric() {
		super("Northshire Cleric",-1,1,1,3);
		addHealEffect(new NorthshireClericHE());
	}
	
	public NorthshireCleric(Minion m) {
		super(m);
	}
	
}