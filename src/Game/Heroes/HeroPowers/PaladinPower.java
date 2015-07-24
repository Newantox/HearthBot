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
		return 0;
	}
	
	public int getCost() {
		return manacost;
	}
	
	@Override
	public boolean useable(BoardState oldstate) {
		return (oldstate.getMySide()[6] == null) && (oldstate.getCurrentMana() >= manacost);
	}

	@Override
	public State result(BoardState oldstate) {
		int i = 0;
		while (oldstate.getMySide()[i]!=null) i++;
		Minion[] newMySide = new Minion[7];
		for (int j = 0; j<i; j++) newMySide[j] = oldstate.getMySide()[j];
		newMySide[i] = new SilverHandRecruit(i);
		Hero hero = (oldstate.getHero()).fresh();
		hero.setPowerUsed(true);
		
		return new BoardState(hero, oldstate.getEnemy(), oldstate.getCurrentMana()-manacost, oldstate.getTotalMana(), oldstate.getOppSide(), newMySide, oldstate.getMyDeck(), oldstate.getMyHand());
	}

	@Override
	public void print() {
		System.out.print("Uther uses hero power");

	}

}
