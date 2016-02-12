package Game.Minions;

public class YoungDragonhawk extends Minion {

	public YoungDragonhawk() {
		super("Young Dragonhawk",1,1,1);
		setRace(Race.BEAST);
		setMaxAttacks(2);
	}
	
	public YoungDragonhawk(Minion m) {
		super(m);
	}
	
}
