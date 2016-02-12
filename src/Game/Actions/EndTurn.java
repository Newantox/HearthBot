package Game.Actions;

import Game.BoardState;
import Game.MyTurnState;
import Search.Action;

public class EndTurn implements Action {

	@Override
	public double cost() {
		return 0;
	}

	@Override
	public MyTurnState result(BoardState oldstate) {
		
		MyTurnState tempstate = oldstate.endTurn(oldstate.getHero());
		tempstate = tempstate.doStartTurnEffects(oldstate.getEnemy());
	
		return tempstate;
		
	}

	@Override
	public void print() {
		System.out.println("Turn ended.");
		
	}
	
	public String output() {
		String newline = System.getProperty("line.separator");
		String s = "Turn ended."+newline;
		return s;
	}


}
