package Game.Actions;

import Game.BoardState;
import Game.Heroes.Hero;
import Game.Minions.Minion;
import Game.Weapons.Weapon;
import Search.Action;
import Search.State;

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
	
	public State result(BoardState oldstate) {
		return (hero.getWeapon()).attackWith(oldstate,defender);
	}
	
	public void print() {
		System.out.print("Hero ");
		System.out.print(hero.getName());
		System.out.print(" attacks enemy minion ");
		System.out.println(defender.getName());
		System.out.println();
	}
	
}
