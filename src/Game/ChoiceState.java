package Game;

import java.util.Set;

import Search.Action;
import Search.State;

public class ChoiceState implements State {

	private BoardState state;
	private Set<Action> actions;
	
	public StateType getStatetype() {
		return StateType.CHOICE;
	}
	
	public BoardState getState() {
		return state;
	}
	
	public ChoiceState(BoardState state, Set<Action> actions) {
		this.state = state;
		this.actions = actions;
	}

	@Override
	public Set<Action> getApplicableActions() {
		return actions;
	}

	@Override
	public State getActionResult(Action action) {
		return action.result(state);
	}

	@Override
	public State damageRandomHittable(TargetsType targets, int i, double j) {
		return state.damageRandomHittable(targets, i,j);
	}
	
	@Override
	public State drawCard() {
		return state.drawCard();
	}
	
}
