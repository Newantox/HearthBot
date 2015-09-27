 package Game.Heroes.HeroPowers;

import Game.BoardState;
import Game.MyTurnState;
import Game.Heroes.Hero;
import Game.Minions.Minion;
import Game.Minions.SilverHandRecruit;

public class PaladinPower extends HeroPower {
	
	private int manacost = 2;
	
	public int getCost() {
		return manacost;
	}
	
	@Override
	public boolean useable(BoardState oldstate) {
		return (oldstate.numberOfAlliedMinions()<7) && ((oldstate.getHero()).getCurrentMana() >= manacost);
	}

	@Override
	public MyTurnState result(BoardState oldstate) {
		Hero hero = (oldstate.getHero()).fresh();
		hero.setPowerUsed(true);
		hero.setCurrentMana(hero.getCurrentMana()-manacost);
		
		BoardState tempstate = new BoardState(oldstate.getViewType(), hero, oldstate.getEnemy(),oldstate.getOppSide(), oldstate.getMySide(), oldstate.getPositionsInPlayOrder(), oldstate.getEnemyHandSize());
	
		Minion minion = new SilverHandRecruit(tempstate.numberOfAlliedMinions());
		return  minion.place(tempstate);
	}

	@Override
	public void print() {
		System.out.print("Uther uses hero power");

	}

}
