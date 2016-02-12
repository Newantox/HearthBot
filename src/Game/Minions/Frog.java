package Game.Minions;

public class Frog extends Minion {

	public Frog() {
		super("Frog",0,1,1);
		setRace(Race.BEAST);
		setTaunt(true);
	}
	
	public Frog(Minion m) {
		super(m);
	}
	
}