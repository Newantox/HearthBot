package Game.Cards.Minions;

import Game.Minions.WaterElemental;
import Game.Minions.Minion;

public class WaterElementalCard extends MinionCard {

	public WaterElementalCard() {
		super("WaterElemental",4);
	}
	
	public Minion makeNew(int target) {
		return new WaterElemental(target);
	}

}