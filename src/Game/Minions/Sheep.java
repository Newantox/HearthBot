package Game.Minions;

public class Sheep extends Minion {

	public Sheep(int target) {
		super("Sheep",target,0,1,1);
		setRace(Race.BEAST);
	}
	
	public Sheep(Minion m) {
		super(m);
	}
	
}