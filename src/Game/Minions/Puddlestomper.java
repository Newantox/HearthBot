package Game.Minions;

public class Puddlestomper extends Minion {

	public Puddlestomper() {
		super("Puddle Stomper",-1,2,3,2);
		setRace(Race.MURLOC);
	}
	
	public Puddlestomper(Minion m) {
		super(m);
	}
	
}
