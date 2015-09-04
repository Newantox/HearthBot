package Game.Actions;

import Game.BoardState;
import Game.Minions.Minion;
import Search.Action;
import Search.State;

public class Attack implements Action {
	
	Minion attacker;
	Minion defender;
	
	public Attack(Minion attacker , Minion defender) {
		this.attacker = new Minion(attacker);
		this.defender = new Minion(defender);
	}

	@Override
	public double cost() {
		//return Math.min(attacker.getHP() , defender.getAtk());
		return 0;
	}

	@Override
	public State result(BoardState oldstate) {
		BoardState tempstate = defender.damage(oldstate,attacker.getAtk()+attacker.getTempAtkChange());
		attacker.setReady(false);
		return attacker.damage(tempstate, defender.getAtk()+defender.getTempAtkChange());
	
	}
	
	public void print() {
		System.out.print("Minion ");
		System.out.print(attacker.getName());
		System.out.print(" attacks enemy minion ");
		System.out.println(defender.getName());
		System.out.println();
	}

}
