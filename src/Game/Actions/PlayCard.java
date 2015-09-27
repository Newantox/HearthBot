package Game.Actions;

import Game.BoardState;
import Game.Card;
import Game.Hand;
import Game.MyTurnState;
import Game.Heroes.Hero;
import Search.Action;

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
		return card.getCost()*0.4;
	}

	@Override
	public MyTurnState result(BoardState oldstate) {
		
		Hero newHero = (oldstate.getHero()).fresh();
		Hand newMyHand = newHero.getMyHand();
		
		newMyHand = newMyHand.remove(pos);
		
		BoardState tempstate = newHero.useMana(oldstate,card.getCost());
		
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
