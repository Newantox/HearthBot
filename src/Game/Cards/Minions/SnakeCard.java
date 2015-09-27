package Game.Cards.Minions;

import Game.Minions.Snake;
import Game.Minions.Minion;

public class SnakeCard extends MinionCard {

	public SnakeCard() {
		super("Snake",0);
	}
	
	public Minion makeNew(int target) {
		return new Snake(target);
	}

}

