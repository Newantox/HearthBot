package Game.Cards.Minions;

import Game.Minions.StormwindKnight;
import Game.Minions.Minion;

public class StormwindKnightCard extends MinionCard {

	public StormwindKnightCard() {
		super("StormwindKnight",4);
	}
	
	public Minion makeNew(int target) {
		return new StormwindKnight(target);
	}

}