package Game.Cards.Minions;

import Game.Minions.MechanicalDragonling;
import Game.Minions.Minion;

public class MechanicalDragonlingCard extends MinionCard {

	public MechanicalDragonlingCard() {
		super("Mechanical Dragonling",1);
	}
	
	public Minion makeNew(int target) {
		return new MechanicalDragonling(target);
	}

}