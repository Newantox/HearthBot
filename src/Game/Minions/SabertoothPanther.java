package Game.Minions;

public class SabertoothPanther extends Minion {

	public SabertoothPanther(int target) {
		super("Sabertooth Panther",target,2,3,2);
		setRace(Race.BEAST);
		setStealth(true);
	}
	
	public SabertoothPanther(Minion m) {
		super(m);
	}
	
}