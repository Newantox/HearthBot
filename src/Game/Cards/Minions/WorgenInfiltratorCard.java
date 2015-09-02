package Game.Cards.Minions;

import Game.Minions.Minion;
import Game.Minions.WorgenInfiltrator;

public class WorgenInfiltratorCard extends MinionCard {

	public WorgenInfiltratorCard() {
		super("Worgen Infiltrator",1);
	}
	
	public Minion makeNew(int target) {
		return new WorgenInfiltrator(target);
	}

}