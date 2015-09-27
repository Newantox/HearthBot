package Game.Cards.Minions;

import Game.Minions.Minion;
import Game.Minions.StonetuskBoar;

public class StonetuskBoarCard extends MinionCard {

	public StonetuskBoarCard() {
		super("Stonetusk Boar",1);
	}
	
	public Minion makeNew(int target) {
		return new StonetuskBoar(target);
	}

}