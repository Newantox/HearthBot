package Game.Minions;

public class SabertoothLion extends Minion {

	public SabertoothLion(int target) {
		super("Sabertooth Lion",target,2,2,1);
		setRace(Race.BEAST);
		setCharge(true);
	}
	
	public SabertoothLion(Minion m) {
		super(m);
	}
	
}