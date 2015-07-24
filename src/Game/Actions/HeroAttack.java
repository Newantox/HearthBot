package Game.Actions;

import Game.BoardState;
import Game.Weapon;
import Game.Heroes.Hero;
import Game.Minions.Minion;
import Search.Action;
import Search.State;

public class HeroAttack implements Action {
	
	private Hero hero;
	private Minion defender;
	private Weapon weapon;

	public HeroAttack(Hero hero, Minion defender) {
		this.hero = hero.fresh();
		this.defender = new Minion(defender);
		this.weapon = new Weapon(hero.getWeapon());
	}

	@Override
	public double cost() {
		return (0.5*weapon.getAtk()) + ((3*defender.getAtk())/hero.getHP());
	}
	
	public State result(BoardState oldstate) {
		BoardState tempstate = defender.damage(oldstate,weapon.getAtk());
		if(defender.getAtk() >= hero.getHP()) {
			return null;
		}
		else {
			hero.setReady(false);
			hero.setHP(hero.getHP() - defender.getAtk());
			weapon.setDurability(weapon.getDurability()-1);
			if (weapon.getDurability()<=0) hero.destroyWeapon();
			else hero.setWeapon(weapon);
			}
		
		State newState = new BoardState(hero,tempstate.getEnemy(),tempstate.getCurrentMana(),tempstate.getTotalMana(),tempstate.getOppSide(),tempstate.getMySide(),tempstate.getMyDeck(),tempstate.getMyHand());
		
		return newState;
	}
	
	public void print() {
		System.out.print("Hero ");
		System.out.print(hero.getName());
		System.out.print(" attacks enemy minion ");
		System.out.println(defender.getName());
		System.out.println();
	}
	
}
