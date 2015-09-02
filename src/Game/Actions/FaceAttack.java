package Game.Actions;

import Game.BoardState;
import Game.Heroes.Hero;
import Game.Minions.Minion;
import Search.Action;
import Search.State;

public class FaceAttack implements Action {
	
	private Minion attacker;
	private Hero defender;
	
	public FaceAttack(Minion attacker, Hero defender) {
		this.attacker = new Minion(attacker);
		this.defender = defender.fresh();
	}

	@Override
	public double cost() {
		return 0;
	}

	@Override
	public State result(BoardState oldstate) {
		
		Minion[] newMySide = new Minion[7];
		for (int i = 0; i<7; i++) {
			if (oldstate.getMySide()[i] != null) newMySide[i] = oldstate.getMySide()[i];
		}
		
		defender.setHP(defender.getHP() - attacker.getAtk() - attacker.getTempAtkChange());
		attacker.setReady(false);
		newMySide[attacker.getMyPos()] = attacker;
		
		return new BoardState(oldstate.getHero(),defender,oldstate.getOppSide(),newMySide,oldstate.getMyDeck(),oldstate.getMyHand());
	}

	@Override
	public void print() {
		System.out.print("Minion ");
		System.out.print(attacker.getName());
		System.out.print(" attacks enemy hero ");
		System.out.println(defender.getName());
		System.out.println();
	}

}
