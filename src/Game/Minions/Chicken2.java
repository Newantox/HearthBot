package Game.Minions;

public class Chicken2 extends Minion {

	public Chicken2(int target) {
		super("Chicken2",target,1,1,1);
		setRace(Race.BEAST);
	}
	
	public Chicken2(Minion m) {
		super(m);
	}
	
}