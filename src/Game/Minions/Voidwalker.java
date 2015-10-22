package Game.Minions;

public class Voidwalker extends Minion {

	public Voidwalker() {
		super("Voidwalker",-1,1,1,3);
		setRace(Race.DEMON);
		setTaunt(true);
	}
	
	public Voidwalker(Minion m) {
		super(m);
	}
	
}
