package Game.Actions;

import Game.BoardState;
import Game.MyTurnState;
import Game.Heroes.Hero;
import Game.Minions.Minion;
import Game.Weapons.Weapon;
import Search.Action;

public class HeroAttack implements Action {
	
	private Hero hero;
	private Minion defender;
	private Weapon weapon;

	public HeroAttack(Hero hero, Minion defender) {
		this.hero = hero;
		this.defender = defender;
		this.weapon = hero.getWeapon();
	}

	@Override
	public double cost() {
		return (0.3*weapon.getAtk()); // + ((3*defender.getAtk())/hero.getHP());
	}
	
	public MyTurnState result(BoardState oldstate) {
		return oldstate.heroAttack(weapon.getId(),defender);
	}
	
	public void print() {
		System.out.print("Hero ");
		System.out.print(hero.getName());
		System.out.print(" attacks enemy minion ");
		System.out.println(defender.getName());
		System.out.println();
	}
	
	public String output() {
		String newline = System.getProperty("line.separator");
		String s = "Hero "+hero.getName()+" attacks enemy minion "+defender.getName()+newline;
		return s;
	}

	
}
