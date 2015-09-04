package Game.Cards.Minions;

import Game.BoardState;
import Game.Card;
import Game.CardType;
import Game.Minions.Minion;
import Search.State;

public abstract class MinionCard implements Card {
	private String name;
	private int cost;

	public MinionCard(String name,int cost) {
		this.name = name;
		this.cost = cost;
	}

	public String getName() {
		return name;
	}

	public int getCost() {
		return cost;
	}
	
	public State playCard(BoardState oldstate, int target) {
		Minion newMinion = this.makeNew(target);

		return newMinion.play(oldstate);
	}

	protected abstract Minion makeNew(int target);
	
	@Override
	public boolean equals(Object that) {
		MinionCard other = (MinionCard)that;
		if (name != other.getName()) return false;
		if (cost != other.getCost()) return false;
		return true;
	}

	@Override
	public CardType getType() {
		return CardType.MINION;
	} 

}
