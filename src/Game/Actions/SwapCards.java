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
		return improvement;
	}

	@Override
	public MyTurnState result(BoardState oldstate) {
		
		int turn;
		
		Hero newHero = (oldstate.getHero()).fresh();
		Hand newHand = newHero.getMyHand();
		Deck newDeck = newHero.getMyDeck();
		
		MyTurnState newState = oldstate;
		
		if (newHand.getSize()==3) turn = 1;
		else {turn = 2; newHand.add(4,new TheCoin());}
		
		for (int position : positions) {
			newDeck = newDeck.add((newHand.raw()).get(position), 1);
			newHand = newHand.remove(position);
		}
		
		newHero.setMyDeck(newDeck);
		newHero.setMyHand(newHand);
	
		newState = new BoardState(oldstate.getViewType(),newHero,oldstate.getEnemy(),oldstate.getOppSide(),oldstate.getMySide(),oldstate.getPositionsInPlayOrder(),oldstate.getEnemyHandSize());
	
		for (int position : positions) {
			newState = newState.drawCard(position);
		}
	
		if (turn==2) newState.setTurnEnded(true);
		
		return newState;
	
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

}
