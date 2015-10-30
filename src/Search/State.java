package Search;

import java.util.Set;

import Game.MyTurnState;

public interface State {
	
	Set<Action> getApplicableActions();
	MyTurnState getActionResult(Action action);
	
	double getValue(Node n);
	double getBestValue(Node node);
	
	boolean isGameWon();
	void print();
	boolean isTurnEnded();

	
}
