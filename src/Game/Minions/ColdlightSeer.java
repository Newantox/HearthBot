package Game.Minions;

import Game.Battlecrys.ColdlightSeerBC;

public class ColdlightSeer extends Minion {

	public ColdlightSeer(int target) {
		super("Coldlight Seer",target,3,2,3,3);
		setRace(Race.MURLOC);
		battlecrys.add(new ColdlightSeerBC());
	}
	
	public ColdlightSeer(Minion m) {
		super(m);
	}
	
}
