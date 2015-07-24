package Game.Cards.Minions;

import Game.BoardState;
import Game.Card;
import Game.Minions.Minion;
import Search.State;

public abstract class MinionCard implements Card {
	private String name;
	private int cost;
	private int hp;
	private int atk;
	private boolean charge;

	public MinionCard() {
		name = "";
		cost = 0;
		hp = 1;
		atk = 1;
		charge = false;
	}

	public String getName() {
		return null;
	}

	public int getCost() {
		return 0;
	}

	public int getAtk() {
		return 0;
	}

	public int getHp() {
		return 0;
	}
	
	public State playCard(BoardState oldstate, int target, int pos) {
		Minion newMinion = this.makeNew(target);
		
		Card[] newMyHand = new Card[10];
		for (int i = 0; i<pos; i++) newMyHand[i] = oldstate.getMyHand()[i];
		for (int i = pos; i<9; i++) newMyHand[i] = oldstate.getMyHand()[i+1];
		
		BoardState tempstate = new BoardState(oldstate.getHero(),oldstate.getEnemy(),oldstate.getCurrentMana()-cost,oldstate.getTotalMana(),oldstate.getOppSide(),oldstate.getMySide(),oldstate.getMyDeck(),newMyHand);
		
		return newMinion.play(tempstate);
	}

	private Minion makeNew(int target) {
		return new Minion(target);
	}
	

}
