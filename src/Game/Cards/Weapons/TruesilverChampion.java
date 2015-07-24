package Game.Cards.Weapons;

import Game.BoardState;
import Game.Card;
import Game.Weapon;
import Game.Heroes.Hero;
import Search.State;

public class TruesilverChampion extends WeaponCard {
	
	String name = "Truesilver Champion";
	int damage = 4;
	int cost = 4;
	int durability =2;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getCost() {
		return cost;
	}

	@Override
	public int getDamage() {
		return damage;
	}

	@Override
	public int getDurability() {
		return durability;
	}

	@Override
	public State playCard(BoardState oldstate, int target, int pos) {
		Hero newhero = new Hero(oldstate.getHero());
		newhero.setWeapon(new Weapon(name,damage,durability));
		
		Card[] newMyHand = new Card[10];
		for (int i = 0; i<pos; i++) newMyHand[i] = oldstate.getMyHand()[i];
		for (int i = pos; i<9; i++) newMyHand[i] = oldstate.getMyHand()[i+1];
		
		return new BoardState(newhero,oldstate.getEnemy(),oldstate.getCurrentMana()-cost,oldstate.getTotalMana(),oldstate.getOppSide(),oldstate.getMySide(),oldstate.getMyDeck(),newMyHand);
	}

}
