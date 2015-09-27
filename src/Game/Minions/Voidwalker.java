package Game.Minions;

public class Voidwalker extends Minion {

	public Voidwalker(int target) {
		super("Voidwalker",target,1,1,3);
		setRace(Race.DEMON);
		setTaunt(true);
	}
	
	public Voidwalker(Minion m) {
		super(m);
	}
	
}
