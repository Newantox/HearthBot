package Game.Minions;

public class YoungDragonhawk extends Minion {

	public YoungDragonhawk(int target) {
		super("Young Dragonhawk",target,1,1,1);
		setRace(Race.BEAST);
		setWindfury(true);
	}
	
	public YoungDragonhawk(Minion m) {
		super(m);
	}
	
}
