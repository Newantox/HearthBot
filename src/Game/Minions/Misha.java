package Game.Minions;

public class Misha extends Minion {

	public Misha() {
		super("Misha",-1,3,4,4);
		setRace(Race.BEAST);
		setTaunt(true);
	}
	
	public Misha(Minion m) {
		super(m);
	}
	
}