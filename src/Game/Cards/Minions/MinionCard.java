package Game.Cards.Minions;

import Game.BoardState;
import Game.Card;
import Game.CardType;
import Game.MyTurnState;
import Game.Minions.Minion;

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
	
	public MyTurnState playCard(BoardState oldstate, int target) {
		Minion newMinion = this.makeNew(target);

		return newMinion.play(oldstate);
	}

	protected abstract Minion makeNew(int target);

	@Override
	public CardType getType() {
		return CardType.MINION;
	} 
	
	@Override
	public boolean equals(Object that) {
		if (this == that) return true;
		else if (that == null) return false;
		else if (getClass() != that.getClass()) return false;
		else {
			final MinionCard other = (MinionCard) that;
			if (!name.equals(other.getName())) return false;
			if (cost!=(other.getCost())) return false;
		}
		return true;
	}

}
