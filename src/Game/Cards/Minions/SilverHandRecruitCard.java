package Game.Cards.Minions;

import Game.Minions.Minion;
import Game.Minions.SilverHandRecruit;

public class SilverHandRecruitCard extends MinionCard {
	private String name;
	private int cost;

	public SilverHandRecruitCard() {
		name = "Southsea Deckhand";
		cost = 1;
	}
	
	public Minion makeNew(int target) {
		return new SilverHandRecruit(target);
	}
}

