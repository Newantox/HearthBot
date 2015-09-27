package Game.Cards.Minions;

import Game.Minions.LordOfTheArena;
import Game.Minions.Minion;

public class LordOfTheArenaCard extends MinionCard {

	public LordOfTheArenaCard() {
		super("Lord Of The Arena",6);
	}
	
	public Minion makeNew(int target) {
		return new LordOfTheArena(target);
	}

}