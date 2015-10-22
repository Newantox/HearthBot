package Game.Cards.Spells;

import Game.CardType;
import Game.PlayableCard;

public abstract class SpellCard implements PlayableCard {
	private String name;
	private int cost;
	
	public SpellCard(String name,int cost) {
		this.name = name;
		this.cost = cost;
	}

	public String getName() {
		return name;
	}

	public int getCost() {
		return cost;
	}
	
	public abstract CardType getType();
	
	@Override
	public boolean equals(Object that) {
		if (this == that) return true;
		else if (that == null) return false;
		else if (getClass() != that.getClass()) return false;
		else {
			final SpellCard other = (SpellCard) that;
			if (!name.equals(other.getName())) return false;
			if (cost!=(other.getCost())) return false;
		}
		return true;
	}
	
}