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
		
		MyTurnState tempstate = oldstate.doEndTurnEffects(oldstate.getHero());
		tempstate = tempstate.doStartTurnEffects(oldstate.getEnemy());
		tempstate.setTurnEnded(true);
		return tempstate;
		
	}

	@Override
	public void print() {
		System.out.println("Turn ended.");
		
	}

}
