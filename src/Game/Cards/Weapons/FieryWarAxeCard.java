package Game.Cards.Weapons;

import Game.Weapons.FieryWarAxe;
import Game.Weapons.Weapon;

public class FieryWarAxeCard extends WeaponCard {
	
	public FieryWarAxeCard() {
		super("Fiery War Axe",2);
	}

	@Override
	public Weapon makeNew() {
		return new FieryWarAxe();
	}
	
}
