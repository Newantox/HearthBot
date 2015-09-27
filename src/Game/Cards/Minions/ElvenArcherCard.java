package Game.Cards.Minions;

import Game.Minions.ElvenArcher;
import Game.Minions.Minion;

public class ElvenArcherCard extends MinionCard {

	public ElvenArcherCard() {
		super("Elven Archer",1);
	}
	
	public Minion makeNew(int target) {
		return new ElvenArcher(target);
	}

}