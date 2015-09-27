package Game.Minions;

public class Boar extends Minion {

	public Boar(int target) {
		super("Boar",target,1,1,1);
		setRace(Race.BEAST);
	}
	
	public Boar(Minion m) {
		super(m);
	}
	
}