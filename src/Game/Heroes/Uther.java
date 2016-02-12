package Game.Heroes;

import Game.Deck;
import Game.Hand;
import Game.TargetsType;
import Game.Heroes.HeroPowers.HeroPower;
import Game.Heroes.HeroPowers.PaladinPower;
import Game.Weapons.Weapon;

public class Uther extends Hero {

	public Uther(String name, TargetsType side, int HP, int maxHP, int Armour, int currentMana, int totalMana, Hand myHand, Deck myDeck, int overload, int fatigue, Weapon weapon) {
		super(name, side, HP, maxHP, Armour, currentMana, totalMana, myHand, myDeck, overload, fatigue, weapon);
		setHeroPower(new PaladinPower());
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
