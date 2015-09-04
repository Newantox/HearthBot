package Game.Cards.Weapons;

import Game.BoardState;
import Game.Card;
import Game.CardType;
import Game.Heroes.Hero;
import Game.Weapons.Weapon;
import Search.State;

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
	
	public State playCard(BoardState oldstate, int target) {
		Hero newhero = new Hero(oldstate.getHero());
		newhero.setWeapon(this.makeNew());
		
		return new BoardState(newhero,oldstate.getEnemy(),oldstate.getOppSide(),oldstate.getMySide(),oldstate.getMyDeck(),oldstate.getMyHand(),oldstate.getSummonEffects(),oldstate.getEnemyHandSize());
	}
	
	public Weapon makeNew() {
		return new Weapon(null);
	}
	
	public CardType getType() {
		return CardType.WEAPON;
	}
	
	@Override
	public boolean equals(Object that) {
		WeaponCard other = (WeaponCard)that;
		if (name != other.getName()) return false;
		if (cost != other.getCost()) return false;
		return true;
	} 
}
