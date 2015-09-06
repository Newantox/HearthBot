package Game.Inspires;

import Game.BoardState;
import Game.Minions.Minion;
import Game.Weapons.Weapon;
import Search.State;

public abstract class Inspire {
	public abstract State perform(Minion minion, BoardState oldstate);

	public abstract State perform(Weapon weapon, BoardState tempstate);
	
}
