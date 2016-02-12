package Game.Actions;

import Game.BoardState;
import Game.MyTurnState;
import Game.Heroes.Hero;
import Game.Minions.Minion;
import Search.Action;

public class FaceAttack implements Action {
	
	private Minion attacker;
	private Hero defender;
	
	public FaceAttack(Minion attacker, Hero defender) {
		this.attacker = attacker;
		this.defender = defender;
	}

	@Override
	public double cost() {
		return 0;
	}

	@Override
	public MyTurnState result(BoardState oldstate) {
		return attacker.attackHero(oldstate, defender);
	}

	@Override
	public void print() {
		System.out.print("Minion ");
		System.out.print(attacker.getName());
		System.out.print(" attacks enemy hero ");
		System.out.println(defender.getName());
		System.out.println();
	}
	
	public String output() {
		String newline = System.getProperty("line.separator");
		String s = "Minion "+attacker.getName()+" attacks enemy hero "+defender.getName()+newline;
		return s;
	}


}
