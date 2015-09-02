package Game.Cards.Minions;

import Game.Minions.Minion;
import Game.Minions.SilverHandRecruit;

public class SilverHandRecruitCard extends MinionCard {

	public SilverHandRecruitCard() {
		super("Silver Hand Recruit",1);
	}
	
	public Minion makeNew(int target) {
		return new SilverHandRecruit(target);
	}
}

