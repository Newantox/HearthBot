package Game.Minions;

public class Squirrel extends Minion {

	public Squirrel() {
		super("Squirrel",1,1,1);
		setRace(Race.BEAST);
	}
	
	public Squirrel(Minion m) {
		super(m);
	}
	
}
