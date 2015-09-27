package Game.Minions;

public class Frog extends Minion {

	public Frog(int target) {
		super("Snake",target,0,1,1);
		setRace(Race.BEAST);
		setTaunt(true);
	}
	
	public Frog(Minion m) {
		super(m);
	}
	
}