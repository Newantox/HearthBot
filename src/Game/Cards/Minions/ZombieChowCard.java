package Game.Cards.Minions;

import Game.Minions.Minion;
import Game.Minions.ZombieChow;

public class ZombieChowCard extends MinionCard {

	public ZombieChowCard() {
		super("Zombie Chow", 1);
	}

	@Override
	protected Minion makeNew(int target) {
		return new ZombieChow(target);
	}
}
