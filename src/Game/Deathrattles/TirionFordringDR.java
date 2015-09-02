package Game.Deathrattles;

import Game.BoardState;
import Game.Minions.Minion;
import Game.Weapons.Ashbringer;
import Game.Weapons.Weapon;
import Search.State;

public class TirionFordringDR extends Deathrattle {

	@Override
	public State perform(Minion minion, BoardState oldstate) {
		Weapon weapon = new Ashbringer();
		if (minion.getMyPos() < 7) return (oldstate.getHero()).equipWeapon(oldstate,weapon);
		else return (oldstate.getEnemy()).equipWeapon(oldstate,weapon);
	}
	
}
