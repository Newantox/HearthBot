package Game.Heroes.HeroPowers;

import Game.BoardState;
import Game.Heroes.Hero;
import Search.State;

public class HunterPower implements HeroPower {

	private int manacost = 2;
	private int damage = 2;

	@Override
	public double cost() {
		return 0.4;
	}
	
	public int getCost() {
		return manacost;
	}
	
	@Override
	public boolean useable(BoardState oldstate) {
		return ((oldstate.getHero()).getCurrentMana() >= manacost);
	}

	@Override
	public State result(BoardState oldstate) {
		Hero hero = (oldstate.getHero()).fresh();
		hero.setPowerUsed(true);
		hero.setCurrentMana(hero.getCurrentMana()-manacost);
		
		BoardState tempstate = new BoardState(hero, oldstate.getEnemy(),oldstate.getOppSide(), oldstate.getMySide(), oldstate.getMyDeck(), oldstate.getMyHand(),oldstate.getSummonEffects(),oldstate.getEnemyHandSize());
	
		return (tempstate.getEnemy()).damage(tempstate, damage);
	}

	@Override
	public void print() {
		System.out.print("Rexxar uses hero power");

	}

}
