package Game.Minions;

public class Chicken extends Minion {

	public Chicken(int target) {
		super("Chicken",target,0,1,1);
		setRace(Race.BEAST);
	}
	
	public Chicken(Minion m) {
		super(m);
	}
	
}