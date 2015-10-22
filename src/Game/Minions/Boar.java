package Game.Minions;

public class Boar extends Minion {

	public Boar() {
		super("Boar",-1,1,1,1);
		setRace(Race.BEAST);
	}
	
	public Boar(Minion m) {
		super(m);
	}
	
}