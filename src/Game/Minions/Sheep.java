package Game.Minions;

public class Sheep extends Minion {

	public Sheep() {
		super("Sheep",0,1,1);
		setRace(Race.BEAST);
	}
	
	public Sheep(Minion m) {
		super(m);
	}
	
}