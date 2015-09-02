package Game.Heroes;

import Game.Heroes.HeroPowers.HeroPower;
import Game.Heroes.HeroPowers.PaladinPower;
import Game.Weapons.Weapon;

public class Uther extends Hero {

	public Uther(String name, int mypos, int HP, int maxHP, int Armour, int currentMana, int totalMana, int overload, Weapon weapon) {
		super(name, mypos, HP, maxHP, Armour, currentMana, totalMana, overload, weapon);
	}
	
	public Uther(Hero hero) {
		super(hero);
	}
	
	public Uther fresh() {
		return new Uther(this);
	}
	
	@Override
	public HeroPower getHeroPower() {
		return new PaladinPower();
	}

}
