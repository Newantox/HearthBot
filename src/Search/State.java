package Search;

import java.util.Set;

import Game.MyTurnState;

public interface State {
	
	Set<Action> getApplicableActions(boolean end);
	MyTurnState getActionResult(Action action);
	
	double getValue(Node n, double minionWeight, double hpWeight);
	double getBestValue(Node node, double minionWeight, double hpWeight);
	
	boolean isGameWon();
	void print();
	boolean isTurnEnded();
	
	String output();

	
}
