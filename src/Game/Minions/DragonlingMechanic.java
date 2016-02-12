package Game.Minions;

import Game.Battlecrys.MinionCompanionBC;

public class DragonlingMechanic extends Minion {

	public DragonlingMechanic() {
		super("Dragonling Mechanic",4,2,4);
		battlecrys.add(new MinionCompanionBC(new MechanicalDragonling()));
	}
	
	public DragonlingMechanic(Minion m) {
		super(m);
	}
	
}