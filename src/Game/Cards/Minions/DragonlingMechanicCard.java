package Game.Cards.Minions;

import Game.Minions.DragonlingMechanic;
import Game.Minions.Minion;

public class DragonlingMechanicCard extends MinionCard {

	public DragonlingMechanicCard() {
		super("Dragonling Mechanic",4);
	}
	
	public Minion makeNew(int target) {
		return new DragonlingMechanic(target);
	}

}