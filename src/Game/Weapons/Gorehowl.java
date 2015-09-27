package Game.Weapons;

import Game.BoardState;
import Game.MyTurnState;
import Game.Heroes.Hero;
import Game.Minions.Minion;

public class Gorehowl extends Weapon {
	
	public Gorehowl() {
		super("Gorehowl", 7, 7, 1);
	}
	
	public Gorehowl(Weapon w) {
		super(w);
	}
	
	@Override
	public MyTurnState attackWith(BoardState oldstate, Minion defender) {
		Hero hero = (oldstate.getHero()).fresh();
		hero.setReady(false);
		BoardState tempstate1 = hero.damage(oldstate,defender.getAtk());
		MyTurnState tempstate2 = defender.damage(tempstate1,getAtk());
		
		if (hero.getMyPos()==14) return tempstate2.changeHeroWeaponAtkDurability(-1,0);
		else return tempstate2.changeEnemyWeaponAtkDurability(-1,0);
	}
}
