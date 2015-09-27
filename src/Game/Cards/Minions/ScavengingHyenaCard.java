package Game.Cards.Minions;

import Game.Minions.Minion;
import Game.Minions.ScavengingHyena;

public class ScavengingHyenaCard extends MinionCard {

	public ScavengingHyenaCard() {
		super("Scavenging Hyena",2);
	}
	
	public Minion makeNew(int target) {
		return new ScavengingHyena(target);
	}

}