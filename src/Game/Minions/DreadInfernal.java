package Game.Minions;

import Game.Battlecrys.DreadInfernalBC;

public class DreadInfernal extends Minion {

	public DreadInfernal(int target) {
		super("Dread Infernal",target,6,6,6);
		setRace(Race.DEMON);
		battlecrys.add(new DreadInfernalBC());
	}
	
	public DreadInfernal(Minion m) {
		super(m);
	}
	
}