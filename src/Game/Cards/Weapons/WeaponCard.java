package Game.Cards.Weapons;

import Game.BoardState;
import Game.Card;
import Game.CardType;
import Game.MyTurnState;
import Game.Cards.Minions.MinionCard;
import Game.Weapons.Weapon;

public abstract class WeaponCard implements Card {
	private String name;
	private int cost;

	public WeaponCard(String name,int cost) {
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
		return (oldstate.getHero()).equipWeapon(oldstate, this.makeNew());
	}
	
	public Weapon makeNew() {
		return new Weapon(null);
	}
	
	public CardType getType() {
		return CardType.WEAPON;
	}
	
	@Override
	public boolean equals(Object that) {
		if (this == that) return true;
		else if (that == null) return false;
		else if (getClass() != that.getClass()) return false;
		else {
			final WeaponCard other = (WeaponCard) that;
			if (!name.equals(other.getName())) return false;
			if (cost!=(other.getCost())) return false;
		}
		return true;
	}
}
