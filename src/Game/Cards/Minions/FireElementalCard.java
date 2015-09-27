package Game.Cards.Minions;

import Game.Minions.FireElemental;
import Game.Minions.Minion;

public class FireElementalCard extends MinionCard {

	public FireElementalCard() {
		super("Fire Elemental",6);
	}
	
	public Minion makeNew(int target) {
		return new FireElemental(target);
	}

}