package Game.Actions;

import Game.BoardState;
import Game.MyTurnState;
import Game.Heroes.Hero;
import Search.Action;

public class FaceAttack implements Action {
	
	private int attackerId;
	private Hero defender;
	private int position = -1;
	
	public FaceAttack(int attackerId, Hero defender) {
		this.attackerId = attackerId;
		this.defender = defender;
	}
	
	public FaceAttack(int attackerId, Hero defender, int position) {
		this.attackerId = attackerId;
		this.defender = defender;
		this.position = position;
	}

	@Override
	public double cost() {
		return 0;
	}

	@Override
	public MyTurnState result(BoardState oldstate) {
		System.out.println(position+"Place"+oldstate.findPosition(attackerId));
		return (oldstate.findMinion(attackerId)).attackHero(oldstate, defender);
	}

	@Override
	public void print() {
		System.out.print("Minion ");
		//System.out.print(attacker.getName());
		System.out.print(" attacks enemy hero ");
		System.out.println(defender.getName());
		System.out.println();
	}
	
	public String output() {
		String newline = System.getProperty("line.separator");
		String s = "Minion "+attackerId+" attacks enemy hero "+defender.getName()+newline;
		return s;
	}


}
