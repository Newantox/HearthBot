package Game.Cards.Minions;

import Game.Minions.Minion;
import Game.Minions.StormwindChampion;

public class StormwindChampionCard extends MinionCard {

	public StormwindChampionCard() {
		super("Stormwind Champion",7);
	}
	
	public Minion makeNew(int target) {
		return new StormwindChampion(target);
	}

}