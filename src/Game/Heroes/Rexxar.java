package Game.Heroes;

import Game.Heroes.HeroPowers.HeroPower;
import Game.Heroes.HeroPowers.HunterPower;
import Game.Weapons.Weapon;

public class Rexxar extends Hero {

	public Rexxar(String name, int mypos, int HP, int maxHP, int Armour, int currentMana, int totalMana, int overload, Weapon weapon) {
		super(name, mypos, HP, maxHP, Armour, currentMana, totalMana, overload, weapon);
		setHeroPower(new HunterPower());
	}
	
	public Rexxar(Hero hero) {
		super(hero);
	}
	
	public Rexxar fresh() {
		return new Rexxar(this);
	}
	
	@Override
	public HeroPower getHeroPower() {
		return new HunterPower();
	}
}
