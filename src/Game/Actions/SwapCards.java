package Game.Actions;

import java.util.Set;

import Game.BoardState;
import Game.Deck;
import Game.Hand;
import Game.MyTurnState;
import Game.Cards.Spells.Untargetted.TheCoin;
import Game.Heroes.Hero;
import Search.Action;

public class SwapCards implements Action {
	
	private Set<Integer> positions;
	private int improvement;
	
	public SwapCards(Set<Integer> positions, int improvement) {
		this.positions = positions;
	}

	@Override
	public double cost() {
		return -improvement;
	}

	@Override
	public MyTurnState result(BoardState oldstate) {
		
		Hero newHero = (oldstate.getHero()).fresh();
		Hand newHand = newHero.getMyHand();
		Deck newDeck = newHero.getMyDeck();
		
		boolean flag;
		
		if (newHero.getMyHand().getSize()==3) flag = true;
		else flag = false;
		
		MyTurnState newState = oldstate;
		
		for (int i = newHand.getSize()-1; i>=0; i--) {
			if (positions.contains(i)) {
				newDeck = newDeck.add((newHand.raw()).get(i), 1);
				newHand = newHand.remove(i);
			}
		}
		
		newHero.setMyDeck(newDeck);
		newHero.setMyHand(newHand);
	
		newState = new BoardState(oldstate.getViewType(),newHero,oldstate.getEnemy(),oldstate.getOppSide(),oldstate.getMySide(),oldstate.getIdsInPlayOrder(),oldstate.getEnemyHandSize(),oldstate.isTurnEnded(),oldstate.getIdCounter());
	
		for (int position : positions) {
			newState = newState.drawCard(position);
		}
		
		if (flag) return newState.doStartTurnEffects(newHero);
		else return newState.addCardToMyHand(new TheCoin());
	
	}

	@Override
	public void print() {
		if (positions.size()==0) System.out.println("Keep hand.");
		else {
			String s = "Swap out cards in positions: ";
			for (int position : positions) s = s+" "+position;
			System.out.println(s);
		}
	}
	
	public String output() {
		String newline = System.getProperty("line.separator");
		String s;
		if (positions.size()==0) s = ("Keep hand.");
		else {
			s = "Swap out cards in positions: ";
			for (int position : positions) s = s+" "+position;
			s = s+newline;
		}
		return s;
	}


}
