package Game.Heroes;

import Game.Deck;
import Game.Hand;
import Game.TargetsType;
import Game.Heroes.HeroPowers.HeroPower;
import Game.Heroes.HeroPowers.HunterPower;
import Game.Weapons.Weapon;

public class Rexxar extends Hero {

	public Rexxar(String name, TargetsType side, int HP, int maxHP, int Armour, int currentMana, int totalMana, Hand myHand, Deck myDeck, int overload, int fatigue, Weapon weapon) {
		super(name, side, HP, maxHP, Armour, currentMana, totalMana, myHand, myDeck, overload, fatigue, weapon);
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
