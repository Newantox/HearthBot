package Game.Cards.Minions;

import Game.Minions.NoviceEngineer;
import Game.Minions.Minion;

public class NoviceEngineerCard extends MinionCard {

	public NoviceEngineerCard() {
		super("NoviceEngineer",2);
	}
	
	public Minion makeNew(int target) {
		return new NoviceEngineer(target);
	}

}