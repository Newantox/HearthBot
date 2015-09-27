package Game.Minions;

public class Puddlestomper extends Minion {

	public Puddlestomper(int target) {
		super("Puddle Stomper",target,2,3,2);
		setRace(Race.MURLOC);
	}
	
	public Puddlestomper(Minion m) {
		super(m);
	}
	
}
