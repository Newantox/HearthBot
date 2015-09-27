package Game.Cards.Minions;

import Game.Minions.StarvingBuzzard;
import Game.Minions.Minion;

public class StarvingBuzzardCard extends MinionCard {

	public StarvingBuzzardCard() {
		super("Starving Buzzard",5);
	}
	
	public Minion makeNew(int target) {
		return new StarvingBuzzard(target);
	}

}