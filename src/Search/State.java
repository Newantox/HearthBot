package Search;

import java.util.Set;

import Game.StateType;
import Game.TargetsType;

public interface State {
	
	StateType getStatetype();
	
	Set<Action> getApplicableActions();
	State getActionResult(Action action);
	
	abstract boolean equals(Object that);
	abstract int hashCode();

	State damageRandomHittable(TargetsType targets, int i, double j);

	State drawCard();
	
}
