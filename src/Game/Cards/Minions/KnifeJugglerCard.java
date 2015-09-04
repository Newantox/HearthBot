package Game.Cards.Minions;

import Game.Minions.KnifeJuggler;
import Game.Minions.Minion;

public class KnifeJugglerCard extends MinionCard {
	public KnifeJugglerCard() {
		super("Knife Juggler",2);
	}
	
	public Minion makeNew(int target) {
		return new KnifeJuggler(target);
	}
	
}
