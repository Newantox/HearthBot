package Game.Cards.Minions;

import Game.Minions.MurlocTidehunter;
import Game.Minions.Minion;

public class MurlocTidehunterCard extends MinionCard {
	
	public MurlocTidehunterCard() {
		super("Murloc Tidehunter",2);
	}
	
	public Minion makeNew(int target) {
		return new MurlocTidehunter(target);
	}

}
