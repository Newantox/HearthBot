package Game.Cards.Minions;

import Game.Minions.Archmage;
import Game.Minions.Minion;

public class ArchmageCard extends MinionCard {

	public ArchmageCard() {
		super("Archmage",6);
	}
	
	public Minion makeNew(int target) {
		return new Archmage(target);
	}

}