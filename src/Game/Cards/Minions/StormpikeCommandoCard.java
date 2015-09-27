package Game.Cards.Minions;

import Game.Minions.StormpikeCommando;
import Game.Minions.Minion;

public class StormpikeCommandoCard extends MinionCard {

	public StormpikeCommandoCard() {
		super("Stormpike Commando",5);
	}
	
	public Minion makeNew(int target) {
		return new StormpikeCommando(target);
	}

}