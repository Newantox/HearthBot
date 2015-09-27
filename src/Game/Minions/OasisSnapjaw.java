package Game.Minions;

public class OasisSnapjaw extends Minion {

	public OasisSnapjaw(int target) {
		super("Oasis Snapjaw",target,4,2,7);
		setRace(Race.BEAST);
	}
	
	public OasisSnapjaw(Minion m) {
		super(m);
	}
	
}