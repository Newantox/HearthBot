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
		MyTurnState tempstate = oldstate;
		tempstate.setTurnEnded(true);
		
		tempstate = tempstate.doEndTurnEffects(oldstate.getHero());
		return tempstate.doStartTurnEffects(oldstate.getHero());
		
	}

	@Override
	public void print() {
		System.out.println("Turn ended.");
		
	}

}
