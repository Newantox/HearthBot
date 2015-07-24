package Game.Cards.Minions;

import Game.Minions.Minion;
import Game.Minions.LeperGnome;

public class LeperGnomeCard extends MinionCard {
	private String name;
	private int cost;

	public LeperGnomeCard() {
		name = "Leper Gnome";
		cost = 1;
	}
	
	public Minion makeNew(int target) {
		return new LeperGnome(target);
	}

}