package Game.Cards.Minions;

import Game.Minions.AbusiveSergeant;
import Game.Minions.Minion;

public class AbusiveSergeantCard extends MinionCard {

	public AbusiveSergeantCard() {
		super("Abusive Sergeant",1);
	}
	
	public Minion makeNew(int target) {
		return new AbusiveSergeant(target);
	}
}
