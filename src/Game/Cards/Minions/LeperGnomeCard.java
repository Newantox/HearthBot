package Game.Cards.Minions;

import Game.Minions.Minion;
import Game.Minions.LeperGnome;

public class LeperGnomeCard extends MinionCard {

	public LeperGnomeCard() {
		super("Leper Gnome",1);
	}
	
	public Minion makeNew(int target) {
		return new LeperGnome(target);
	}

}