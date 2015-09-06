package Game.Deathrattles;

import Game.BoardState;
import Game.Weapons.Weapon;
import Search.State;

public abstract class WeaponDeathrattle extends Deathrattle {

	public abstract State perform(Weapon weapon, BoardState oldstate);
	
}
