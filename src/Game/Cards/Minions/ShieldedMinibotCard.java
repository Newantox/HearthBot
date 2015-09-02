package Game.Cards.Minions;

import Game.Minions.Minion;
import Game.Minions.ShieldedMinibot;

public class ShieldedMinibotCard extends MinionCard {

	public ShieldedMinibotCard() {
		super("Shielded Minibot",1);
	}
	
	public Minion makeNew(int target) {
		return new ShieldedMinibot(target);
	}

}