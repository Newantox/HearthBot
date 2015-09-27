package Game.Cards.Minions;

import Game.Minions.FrostwolfGrunt;
import Game.Minions.Minion;

public class FrostwolfGruntCard extends MinionCard {

	public FrostwolfGruntCard() {
		super("Frostwolf Grunt",2);
	}
	
	public Minion makeNew(int target) {
		return new FrostwolfGrunt(target);
	}

}