package Game.Actions;

import Game.BoardState;
import Game.Card;
import Game.Hand;
import Search.Action;
import Search.State;

public class PlayCard implements Action {
	
	private Card card;
	private int target;
	private int pos;
	
	public PlayCard(Card card, int target, int pos) {
		this.card = card;
		this.target = target;
		this.pos = pos;
	}

	@Override
	public double cost() {
		return card.getCost();
	}

	@Override
	public State result(BoardState oldstate) {
		Hand newMyHand = (oldstate.getMyHand()).remove(pos);
		BoardState tempstate = (oldstate.getHero()).useMana(oldstate,card.getCost());
		tempstate = new BoardState(tempstate.getHero(),tempstate.getEnemy(),tempstate.getOppSide(),tempstate.getMySide(),tempstate.getMyDeck(),newMyHand);
		return card.playCard(tempstate,target);
	}

	@Override
	public void print() {
		System.out.print("Play ");
		System.out.print(card.getName());
		System.out.print(" at ");
		System.out.println(target);
		System.out.println();
	}


}
