package Game.Heroes;

import Game.Weapon;
import Game.Heroes.HeroPowers.HeroPower;
import Game.Heroes.HeroPowers.PaladinPower;

public class Uther extends Hero {

	public Uther(String name, int HP, int Armour, Weapon weapon) {
		super(name, HP, Armour, weapon);
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
