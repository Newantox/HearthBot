package Game.Cards.Minions;

import Game.Minions.FlametongueTotem;
import Game.Minions.Minion;

public class FlametongueTotemCard extends MinionCard {

	public FlametongueTotemCard() {
		super("Flametongue Totem",2);
	}
	
	public Minion makeNew(int target) {
		return new FlametongueTotem(target);
	}

}