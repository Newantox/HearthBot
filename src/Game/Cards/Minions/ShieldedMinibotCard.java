package Game.Cards.Minions;

import Game.Minions.Minion;
import Game.Minions.ShieldedMinibot;

public class ShieldedMinibotCard extends MinionCard {
	private String name;
	private int cost;

	public ShieldedMinibotCard() {
		name = "Shielded Minibot";
		cost = 1;
	}
	
	public Minion makeNew(int target) {
		return new ShieldedMinibot(target);
	}

}