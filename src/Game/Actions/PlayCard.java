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
		return 0;
	}

	@Override
	public MyTurnState result(BoardState oldstate) {
		
		Hero newHero = (oldstate.getHero()).fresh();
		Hand newMyHand = newHero.getMyHand();
		
		newMyHand = newMyHand.remove(pos);
		newHero.setMyHand(newMyHand);
		
		BoardState tempstate = new BoardState(oldstate.getViewType(), newHero,oldstate.getEnemy(),oldstate.getOppSide(),oldstate.getMySide(),oldstate.getIdsInPlayOrder(),oldstate.getEnemyHandSize(),oldstate.isTurnEnded(),oldstate.getIdCounter());
		
		tempstate = newHero.useMana(tempstate,card.getCost());
		
		return card.playCard(tempstate,target);
	}

	@Override
	public void print() {
		card.playPrint(target);
	}
	
	public String output() {
		return card.playOutput(target);
	}



}
