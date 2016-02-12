package Game.Minions;

public class SabertoothPanther extends Minion {

	public SabertoothPanther() {
		super("Sabertooth Panther",2,3,2);
		setRace(Race.BEAST);
		setStealth(true);
	}
	
	public SabertoothPanther(Minion m) {
		super(m);
	}
	
}