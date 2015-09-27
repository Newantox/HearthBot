package Game.Cards.Minions;

import Game.Minions.IronforgeRifleman;
import Game.Minions.Minion;

public class IronforgeRiflemanCard extends MinionCard {

	public IronforgeRiflemanCard() {
		super("Ironforge Rifleman",3);
	}
	
	public Minion makeNew(int target) {
		return new IronforgeRifleman(target);
	}

}