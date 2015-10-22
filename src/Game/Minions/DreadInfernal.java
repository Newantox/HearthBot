package Game.Minions;

import Game.Battlecrys.DreadInfernalBC;

public class DreadInfernal extends Minion {

	public DreadInfernal() {
		super("Dread Infernal",-1,6,6,6);
		setRace(Race.DEMON);
		battlecrys.add(new DreadInfernalBC());
	}
	
	public DreadInfernal(Minion m) {
		super(m);
	}
	
}