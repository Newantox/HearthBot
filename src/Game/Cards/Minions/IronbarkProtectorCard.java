package Game.Cards.Minions;

import Game.Minions.IronbarkProtector;
import Game.Minions.Minion;

public class IronbarkProtectorCard extends MinionCard {

	public IronbarkProtectorCard() {
		super("Ironbark Protector",8);
	}
	
	public Minion makeNew(int target) {
		return new IronbarkProtector(target);
	}

}
