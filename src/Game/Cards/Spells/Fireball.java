package Game.Cards.Spells;

import Game.BoardState;
import Game.Card;
import Game.Heroes.Hero;
import Game.Minions.Minion;
import Search.State;

public class Fireball extends SpellCard {
	
	int damage = 6;
	int cost = 4;

	@Override
	public String getName() {
		return "Fireball";
	}

	@Override
	public int getCost() {
		return cost;
	}

	@Override
	public State playCard(BoardState oldstate, int target, int pos) {
		Card[] newMyHand = new Card[10];
		
		if (target <14) {
			Minion defender;
			if (target<7) defender = oldstate.getMySide()[target];
			else defender = oldstate.getOppSide()[target-7];
		
			BoardState tempstate = defender.damage(oldstate,damage);
		    
			for (int i = 0; i<pos; i++) newMyHand[i] = tempstate.getMyHand()[i];
			for (int i = pos; i<9; i++) newMyHand[i] = tempstate.getMyHand()[i+1];
			
			return new BoardState(tempstate.getHero(),tempstate.getEnemy(),tempstate.getCurrentMana()-cost,tempstate.getTotalMana(),tempstate.getOppSide(),tempstate.getMySide(),tempstate.getMyDeck(),newMyHand);
		}
		
		else if (target==14) {
			Hero defender = oldstate.getEnemy().fresh();
			defender.setHP(defender.getHP() - damage);
			
			for (int i = 0; i<pos; i++) newMyHand[i] = oldstate.getMyHand()[i];
			for (int i = pos; i<9; i++) newMyHand[i] = oldstate.getMyHand()[i+1];
			
			return new BoardState(oldstate.getHero(),defender,oldstate.getCurrentMana(),oldstate.getTotalMana(),oldstate.getOppSide(),oldstate.getMySide(),oldstate.getMyDeck(),newMyHand);
		}
		
		else {
			Hero defender = oldstate.getHero().fresh();
			defender.setHP(defender.getHP() - damage);
			
			for (int i = 0; i<pos; i++) newMyHand[i] = oldstate.getMyHand()[i];
			for (int i = pos; i<9; i++) newMyHand[i] = oldstate.getMyHand()[i+1];
			
			return new BoardState(defender,oldstate.getEnemy(),oldstate.getCurrentMana(),oldstate.getTotalMana(),oldstate.getOppSide(),oldstate.getMySide(),oldstate.getMyDeck(),newMyHand);
		}
	}
}
