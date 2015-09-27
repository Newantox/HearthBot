package Game.Deathrattles;

import Game.BoardState;
import Game.MyTurnState;
import Game.Minions.Minion;
import Game.Weapons.Ashbringer;
import Game.Weapons.Weapon;

public class TirionFordringDR extends MinionDeathrattle {

	@Override
	public MyTurnState perform(Minion minion, BoardState oldstate) {
		Weapon weapon = new Ashbringer();
		if (minion.getMyPos() < 7) return (oldstate.getHero()).equipWeapon(oldstate,weapon);
		else return (oldstate.getEnemy()).equipWeapon(oldstate,weapon);
	}
	
}
