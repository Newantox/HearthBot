package Game.Actions;

import Game.BoardState;
import Game.Card;
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
		return 4;
	}

	@Override
	public State result(BoardState oldstate) {
		return card.playCard(oldstate,target,pos);
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
