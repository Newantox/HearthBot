package Search;

import java.util.Set;

import Game.StateType;

public interface State {
	
	Game.StateType statetype = StateType.PLAIN;
	
	StateType getStatetype();
	
	Set<Action> getApplicableActions();
	State getActionResult(Action action);
	
	abstract boolean equals(Object that);
	abstract int hashCode();

	State damageRandomHittableEnemy(int i, double j);
	
}
