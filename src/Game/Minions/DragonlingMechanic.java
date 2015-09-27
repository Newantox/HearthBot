package Game.Minions;

import Game.Battlecrys.DragonlingMechanicBC;

public class DragonlingMechanic extends Minion {

	public DragonlingMechanic(int target) {
		super("Dragonling Mechanic",target,4,2,4);
		battlecrys.add(new DragonlingMechanicBC());
	}
	
	public DragonlingMechanic(Minion m) {
		super(m);
	}
	
}