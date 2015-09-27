package Game.Cards.Minions;

import Game.Minions.FrostwolfWarlord;
import Game.Minions.Minion;

public class FrostwolfWarlordCard extends MinionCard {

	public FrostwolfWarlordCard() {
		super("Frostwolf Warlord",5);
	}
	
	public Minion makeNew(int target) {
		return new FrostwolfWarlord(target);
	}

}