package Game.Cards.Minions;

import Game.Minions.DreadInfernal;
import Game.Minions.Minion;

public class DreadInfernalCard extends MinionCard {

	public DreadInfernalCard() {
		super("Dread Infernal",6);
	}
	
	public Minion makeNew(int target) {
		return new DreadInfernal(target);
	}

}