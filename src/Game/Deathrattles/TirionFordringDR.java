package Game.Deathrattles;

import Game.BoardState;
import Game.MyTurnState;
import Game.PlayableCard;
import Game.TargetsType;
import Game.Weapons.Ashbringer;
import Game.Weapons.Weapon;

public class TirionFordringDR extends Deathrattle {

	@Override
	public MyTurnState perform(BoardState oldstate, PlayableCard minion, TargetsType side) {
		Weapon weapon = new Ashbringer();
		if (side.equals(TargetsType.ALLYCHAR)) return (oldstate.getHero()).equipWeapon(oldstate,weapon);
		else return (oldstate.getEnemy()).equipWeapon(oldstate,weapon);
	}
	
}
