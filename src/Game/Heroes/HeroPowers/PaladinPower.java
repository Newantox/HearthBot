package Game.Heroes.HeroPowers;

import Game.BoardState;
import Game.Heroes.Hero;
import Game.Minions.Minion;
import Game.Minions.SilverHandRecruit;
import Search.State;

public class PaladinPower implements HeroPower {
	
	private int manacost = 2;

	@Override
	public double cost() {
		return 0.4;
	}
	
	public int getCost() {
		return manacost;
	}
	
	@Override
	public boolean useable(BoardState oldstate) {
		return (oldstate.getMySide()[6] == null) && ((oldstate.getHero()).getCurrentMana() >= manacost);
	}

	@Override
	public State result(BoardState oldstate) {
		Hero hero = (oldstate.getHero()).fresh();
		hero.setPowerUsed(true);
		hero.setCurrentMana(hero.getCurrentMana()-manacost);
		
		BoardState tempstate = new BoardState(hero, oldstate.getEnemy(),oldstate.getOppSide(), oldstate.getMySide(), oldstate.getMyDeck(), oldstate.getMyHand(),oldstate.getSummonEffects(),oldstate.getEnemyHandSize());
	
		Minion minion = new SilverHandRecruit(oldstate.numberOfMinions());
		return  minion.place(tempstate);
	}

	@Override
	public void print() {
		System.out.print("Uther uses hero power");

	}

}
