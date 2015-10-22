package Game.Deathrattles;

import Game.BoardState;
import Game.MyTurnState;
import Game.PlayableCard;
import Game.Minions.Minion;
import Game.Weapons.Ashbringer;
import Game.Weapons.Weapon;

public class TirionFordringDR extends Deathrattle {

	@Override
	public MyTurnState perform(PlayableCard minion, BoardState oldstate) {
		Weapon weapon = new Ashbringer();
		if (((Minion) minion).getMyPos() < 7) return (oldstate.getHero()).equipWeapon(oldstate,weapon);
		else return (oldstate.getEnemy()).equipWeapon(oldstate,weapon);
	}
	
}
