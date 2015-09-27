package Game.Minions;

public class Squirrel extends Minion {

	public Squirrel(int target) {
		super("Squirrel",target,1,1,1);
		setRace(Race.BEAST);
	}
	
	public Squirrel(Minion m) {
		super(m);
	}
	
}
