package Game.Actions;

import Game.BoardState;
import Game.Minions.Minion;
import Search.Action;
import Search.State;

public class Attack implements Action {
	
	Minion attacker;
	Minion defender;
	
	public Attack(Minion attacker , Minion defender) {
		this.attacker = attacker;
		this.defender = defender;
	}

	@Override
	public double cost() {
		//return Math.min(attacker.getHP() , defender.getAtk());
		return 0;
	}

	@Override
	public State result(BoardState oldstate) {
		return attacker.attackWith(oldstate, defender);
	
	}
	
	public void print() {
		System.out.print("Minion ");
		System.out.print(attacker.getName());
		System.out.print(" attacks enemy minion ");
		System.out.println(defender.getName());
		System.out.println();
	}

}
