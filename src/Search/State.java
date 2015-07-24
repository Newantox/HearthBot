package Search;

import java.util.Set;

public interface State {
	Set<? extends Action> getApplicableActions();
	State getActionResult(Action action);
	
	abstract boolean equals(Object that);
	abstract int hashCode();
}
