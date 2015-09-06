package Game.Battlecrys;

import Game.BoardState;
import Game.Weapons.Weapon;
import Search.State;

public abstract class WeaponBattlecry extends Battlecry {
	
	public abstract State perform(Weapon weapon, BoardState tempstate);
	
}
