package Game.Cards.Minions;

import Game.Minions.HealingTotem;
import Game.Minions.Minion;

public class HealingTotemCard extends MinionCard {

	public HealingTotemCard() {
		super("Healing Totem",1);
	}
	
	public Minion makeNew(int target) {
		return new HealingTotem(target);
	}

}