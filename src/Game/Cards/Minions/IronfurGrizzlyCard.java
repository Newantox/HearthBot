package Game.Cards.Minions;

import Game.Minions.IronfurGrizzly;
import Game.Minions.Minion;

public class IronfurGrizzlyCard extends MinionCard {

	public IronfurGrizzlyCard() {
		super("Ironfur Grizzly",3);
	}
	
	public Minion makeNew(int target) {
		return new IronfurGrizzly(target);
	}

}