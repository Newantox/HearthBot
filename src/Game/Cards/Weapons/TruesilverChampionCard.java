package Game.Cards.Weapons;

import Game.Weapons.TruesilverChampion;
import Game.Weapons.Weapon;

public class TruesilverChampionCard extends WeaponCard {
	
	public TruesilverChampionCard() {
		super("Truesilver Champion",4);
	}

	@Override
	public Weapon makeNew() {
		return new TruesilverChampion();
	}

}
