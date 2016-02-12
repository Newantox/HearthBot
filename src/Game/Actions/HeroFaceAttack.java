package Game.Actions;

import Game.BoardState;
import Game.MyTurnState;
import Game.Heroes.Hero;
import Game.Weapons.Weapon;
import Search.Action;

public class HeroFaceAttack implements Action {
	
	private Hero attacker;
	private Hero defender;
	Weapon weapon;
	
	
	public HeroFaceAttack(Hero attacker, Hero defender) {
		this.attacker = attacker;
		this.defender = defender;
		this.weapon = attacker.getWeapon();
	}

	@Override
	public double cost() {
		return 0.5*weapon.getAtk();
	}

	@Override
	public MyTurnState result(BoardState oldstate) {
		return oldstate.heroAttack(weapon.getId(),defender);
	}

	@Override
	public void print() {
		System.out.print("Hero ");
		System.out.print(attacker.getName());
		System.out.print(" attacks enemy hero ");
		System.out.println(defender.getName());
		System.out.println();
	}
	
	public String output() {
		String newline = System.getProperty("line.separator");
		String s = "Hero "+attacker.getName()+" attacks enemy hero "+defender.getName()+newline;
		return s;
	}


}
