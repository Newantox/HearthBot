package Game.Minions;

import Game.Battlecrys.ColdlightSeerBC;

public class ColdlightSeer extends Minion {

	public ColdlightSeer() {
		super("Coldlight Seer",3,2,3);
		setRace(Race.MURLOC);
		battlecrys.add(new ColdlightSeerBC());
	}
	
	public ColdlightSeer(Minion m) {
		super(m);
	}
	
}
