package Game.Cards.Spells;

import Game.BoardState;
import Game.Card;
import Game.Minions.Minion;
import Search.State;

public class BlessingOfMight extends SpellCard {

	int cost = 1;

	@Override
	public String getName() {
		return "Blessing of Might";
	}

	@Override
	public int getCost() {
		return cost;
	}

	@Override
	public State playCard(BoardState oldstate, int target, int pos) {
		Card[] newMyHand = new Card[10];
		Minion defender;
		if (target<7) {
			defender = new Minion(oldstate.getMySide()[target]);
			Minion[] newMySide = new Minion[7];
			for (int i = 0; i<7; i++) {
				if (oldstate.getMySide()[i] != null && i!=target) newMySide[i] = oldstate.getMySide()[i];
			}
			newMySide[target] = defender;
			
			defender.setAtk(defender.getAtk() + 3);
			
			for (int i = 0; i<pos; i++) newMyHand[i] = oldstate.getMyHand()[i];
			for (int i = pos; i<9; i++) newMyHand[i] = oldstate.getMyHand()[i+1];
			return new BoardState(oldstate.getHero(),oldstate.getEnemy(),oldstate.getCurrentMana()-cost,oldstate.getTotalMana(),oldstate.getOppSide(),newMySide,oldstate.getMyDeck(),newMyHand);
		}
		
		else {
			defender = oldstate.getOppSide()[target-7];
		    Minion[] newOppSide = new Minion[7];
		    for (int i = 0; i<7; i++) {
		    	if (oldstate.getOppSide()[i] != null && i!=target-7) newOppSide[i] = oldstate.getOppSide()[i];
		    }
		    newOppSide[target-7] = defender;
		    
		    defender.setAtk(defender.getAtk() + 3);
		    
		    for (int i = 0; i<pos; i++) newMyHand[i] = oldstate.getMyHand()[i];
			for (int i = pos; i<9; i++) newMyHand[i] = oldstate.getMyHand()[i+1];
			
		    return new BoardState(oldstate.getHero(),oldstate.getEnemy(),oldstate.getCurrentMana()-cost,oldstate.getTotalMana(),newOppSide,oldstate.getMySide(),oldstate.getMyDeck(),newMyHand);
		}
	}

}
