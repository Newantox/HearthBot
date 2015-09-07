package Game.Cards.Minions;

import Game.Minions.BluegillWarrior;
import Game.Minions.Minion;

public class BluegillWarriorCard extends MinionCard {

	public BluegillWarriorCard(String name, int cost) {
		super("Bluegill Warrior", 2);
	}

	@Override
	protected Minion makeNew(int target) {
		return new BluegillWarrior(target);
	}

}
