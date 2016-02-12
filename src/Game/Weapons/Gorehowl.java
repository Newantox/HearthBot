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
		Hero newHero = oldstate.getHero().fresh();
		newHero.setReady(false);
			
		MyTurnState tempstate = new BoardState(oldstate.getViewType(),newHero,oldstate.getEnemy(),oldstate.getOppSide(),oldstate.getMySide(),oldstate.getIdsInPlayOrder(),oldstate.getEnemyHandSize(),oldstate.isTurnEnded());
		tempstate.damage(newHero,defender.getAtk());
		tempstate = tempstate.damage(defender,newHero.getWeapon().getAtk());
			
		return tempstate.changeHeroWeaponAtkDurability(getId(),0,-1);
	}
}
