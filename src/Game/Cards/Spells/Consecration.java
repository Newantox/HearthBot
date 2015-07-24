package Game.Cards.Spells;

import Game.BoardState;
import Game.Card;
import Game.Heroes.Hero;
import Game.Minions.Minion;
import Search.State;

public class Consecration extends SpellCard {
	int damage = 2;
	int cost = 4;

	@Override
	public String getName() {
		return "Consecration";
	}

	@Override
	public int getCost() {
		return cost;
	}

	@Override
	public State playCard(BoardState oldstate, int target, int pos) {
		
		Hero newenemy = new Hero(oldstate.getEnemy());
		newenemy.setHP(newenemy.getHP()-damage);
		
		BoardState tempstate = oldstate;
		for (int i = 0; i<7; i++) {
			if (tempstate.getOppSide()[i] != null) {
				tempstate = ((tempstate.getOppSide())[i]).damage(tempstate,damage);
			}
		}
			
		Card[] newMyHand = new Card[10];
		for (int i = 0; i<pos; i++) newMyHand[i] = tempstate.getMyHand()[i];
		for (int i = pos; i<9; i++) newMyHand[i] = tempstate.getMyHand()[i+1];
		
		return new BoardState(tempstate.getHero(),newenemy,tempstate.getCurrentMana()-cost,tempstate.getTotalMana(),tempstate.getOppSide(),tempstate.getMySide(),tempstate.getMyDeck(),newMyHand);
	}

}
