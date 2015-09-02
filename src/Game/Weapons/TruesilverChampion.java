package Game.Weapons;

import Game.BoardState;
import Game.Heroes.Hero;
import Game.Minions.Minion;

public class TruesilverChampion extends Weapon {
	
	public TruesilverChampion() {
		super("Truesilver Champion",4,4,2);
	}

	public TruesilverChampion(Weapon w) {
		super(w);
	}
	
	@Override
	public BoardState attackWith(BoardState oldstate, Minion defender) {
		Hero hero = (oldstate.getHero()).fresh();
		Weapon weapon = this.fresh();
		BoardState tempstate = hero.heal(oldstate,2);
		tempstate = defender.damage(tempstate,getAtk());
		hero = tempstate.getHero();
		tempstate = (tempstate.getHero()).damage(tempstate,defender.getAtk());
		hero = tempstate.getHero();
		hero.setReady(false);
		weapon.setDurability(weapon.getDurability()-1);
		if (weapon.getDurability()<=0) return hero.destroyWeapon(tempstate);
		else hero.setWeapon(weapon);
		return new BoardState(hero,tempstate.getEnemy(),tempstate.getOppSide(),tempstate.getMySide(),tempstate.getMyDeck(),tempstate.getMyHand());
	}

}
