package Game;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import Search.Action;
import Search.State;

public class RandomState implements State {
	
	public StateType getStatetype() {
		return StateType.RANDOM;
	}
	
	private List<StateProbabilityPair> states;
	
	public RandomState(List<StateProbabilityPair> states) {
		this.states = states;
	}

	@Override
	public Set<Action> getApplicableActions() {
		Set<Action> actions = new LinkedHashSet<Action>();		
		for (int i = 0; i < states.size(); i++) {
			actions.add(new IndexAction(i));
		}
		return actions;
	}

	@Override
	public State getActionResult(Action action) {
		IndexAction index = (IndexAction)action;
		return (states.get(index.getIndex())).getState();
	}
	
	public int getSize() {
		return states.size();
	}
	
	public StateProbabilityPair getPair(int i) {
		return states.get(i);
	}

	@Override
	public State damageRandomHittableEnemy(int amount, double probmodifier) {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			list.add(new StateProbabilityPair(thing.getState().damageRandomHittableEnemy(amount,probmodifier * thing.getProbability()),thing.getProbability()));
		}
		return new RandomState(list);
	}

}
