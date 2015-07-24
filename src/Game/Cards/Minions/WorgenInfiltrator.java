package Game.Cards.Minions;

import Game.BoardState;
import Game.Card;
import Game.Minions.Minion;
import Search.State;

public class WorgenInfiltrator extends MinionCard {
	private String name;
	private int cost;
	private int hp;
	private int atk;
	private boolean charge;
	private boolean stealth;

	public WorgenInfiltrator() {
		name = "Worgen Infiltrator";
		cost = 1;
		hp = 1;
		atk = 2;
		charge = false;
		stealth = false;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCost() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getAtk() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHp() {
		// TODO Auto-generated method stub
		return 0;
	}

}
