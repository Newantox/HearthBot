package Game.Minions;

import Game.Battlecrys.HungryCrabBC;

public class HungryCrab extends Minion {

	public HungryCrab() {
		super("Hungry Crab",1,1,1);
		setRace(Race.BEAST);
		battlecrys.add(new HungryCrabBC());
	}
	
	public HungryCrab(Minion m) {
		super(m);
	}
	
}