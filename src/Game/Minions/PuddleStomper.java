package Game.Minions;

public class PuddleStomper extends Minion {

	public PuddleStomper(int target) {
		super("Puddle Stomper",target,2,3,2,2);
		setRace(Race.MURLOC);
	}
	
	public PuddleStomper(Minion m) {
		super(m);
	}
	
}
