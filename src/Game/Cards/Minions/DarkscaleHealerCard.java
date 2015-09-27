package Game.Cards.Minions;

import Game.Minions.DarkscaleHealer;
import Game.Minions.Minion;

public class DarkscaleHealerCard extends MinionCard {

	public DarkscaleHealerCard() {
		super("Darkscale Healer",5);
	}
	
	public Minion makeNew(int target) {
		return new DarkscaleHealer(target);
	}

}