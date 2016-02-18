package Game.Actions;

import Game.BoardState;
import Game.MyTurnState;
import Search.Action;

public class Attack implements Action {
	
	int attackerId;
	int defenderId;
	
	public Attack(int attackerId , int defenderId) {
		this.attackerId = attackerId;
		this.defenderId = defenderId;
	}

	@Override
	public double cost() {
		//return Math.min(attacker.getHP() , defender.getAtk());
		return 0;
	}

	@Override
	public MyTurnState result(BoardState oldstate) {
		return (oldstate.findMinion(attackerId)).attackWith(oldstate, oldstate.findMinion(defenderId));
	
	}
	
	public void print() {
		System.out.print("Minion ");
		//System.out.print(attacker.getName());
		System.out.print(" attacks enemy minion ");
		//System.out.println(defender.getName());
		System.out.println();
	}
	
	public String output() {
		String newline = System.getProperty("line.separator");
		String s = "Minion "+attackerId+" attacks enemy minion "+defenderId+newline;
		return s;
	}

}
