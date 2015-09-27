package Game.Minions;

public class Windspeaker extends Minion {

	public Windspeaker(int target) {
		super("Windspeaker",target,4,3,3);
		battlecrys.add(new WindspeakerBC());
	}
	
	public Windspeaker(Minion m) {
		super(m);
	}
	
}