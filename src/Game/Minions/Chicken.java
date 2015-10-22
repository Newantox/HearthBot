package Game.Minions;

public class Chicken extends Minion {

	public Chicken() {
		super("Chicken",-1,0,1,1);
		setRace(Race.BEAST);
	}
	
	public Chicken(Minion m) {
		super(m);
	}
	
}