package Game.Actions;

import Game.BoardState;
import Game.Weapon;
import Game.Heroes.Hero;
import Search.Action;
import Search.State;

public class HeroFaceAttack implements Action {
	
	private Hero attacker;
	private Hero defender;
	Weapon weapon;
	
	
	public HeroFaceAttack(Hero attacker, Hero defender) {
		this.attacker = attacker.fresh();
		this.defender = defender.fresh();
		this.weapon = new Weapon(attacker.getWeapon());
	}

	@Override
	public double cost() {
		return 0.5*weapon.getAtk();
	}

	@Override
	public State result(BoardState oldstate) {
		
		defender.setHP(defender.getHP() - weapon.getAtk());
		attacker.setReady(false);
		weapon.setDurability(weapon.getDurability() - 1);
		if (weapon.getDurability()<=0) attacker.destroyWeapon();
		else attacker.setWeapon(weapon);
		
		State newState = new BoardState(attacker,defender,oldstate.getCurrentMana(),oldstate.getTotalMana(),oldstate.getOppSide(),oldstate.getMySide(),oldstate.getMyDeck(),oldstate.getMyHand());
		return newState;
	}

	@Override
	public void print() {
		System.out.print("Hero ");
		System.out.print(attacker.getName());
		System.out.print(" attacks enemy hero ");
		System.out.println(defender.getName());
		System.out.println();
	}

}
