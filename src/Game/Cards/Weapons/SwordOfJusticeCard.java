package Game.Cards.Weapons;

import Game.Weapons.SwordOfJustice;
import Game.Weapons.Weapon;

public class SwordOfJusticeCard extends WeaponCard {
	public SwordOfJusticeCard() {
		super("Sword of Justice",3);
	}

	@Override
	public Weapon makeNew() {
		return new SwordOfJustice();
	}
}
