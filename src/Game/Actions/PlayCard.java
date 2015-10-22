package Game.Actions;

import Game.BoardState;
import Game.Character;
import Game.Hand;
import Game.MyTurnState;
import Game.PlayableCard;
import Game.Heroes.Hero;
import Search.Action;

public class PlayCard implements Action {
	
	private PlayableCard card;
	private Character target;
	private int pos;
	
	public PlayCard(PlayableCard card, Character target, int pos) {
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
