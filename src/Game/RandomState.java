package Game;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import Game.Battlecrys.Battlecry;
import Game.Deathrattles.Deathrattle;
import Game.Heroes.Hero;
import Game.Minions.Minion;
import Search.Action;
import Search.Node;
import Search.State;

public class RandomState implements State {
	
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
	public State damageRandomHittable(TargetsType targets, int amount) {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			list.add(new StateProbabilityPair(thing.getState().damageRandomHittable(targets,amount),thing.getProbability()));
		}
		return new RandomState(list);
	}
	
	@Override
	public State drawCard() {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			list.add(new StateProbabilityPair((thing.getState()).drawCard(),thing.getProbability()));
		}
		return new RandomState(list);
	}
	
	@Override
	public State performBC(Battlecry battlecry, Minion minion) {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			list.add(new StateProbabilityPair(battlecry.trigger(minion,thing.getState()),thing.getProbability()));
		}
		return new RandomState(list);
	}
	
	@Override
	public State performDR(Deathrattle deathrattle, Minion minion) {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			list.add(new StateProbabilityPair(deathrattle.trigger(minion,thing.getState()),thing.getProbability()));
		}
		return new RandomState(list);
	}
	
	public double getValue(Node n) {
		int j = 0;
		for (int i = 0; i < getSize(); i++) {
			StateProbabilityPair pair = states.get(i);
			Node node = new Node(n.parent,n.action,pair.getState());
			j += pair.getProbability()*getValue(node);
		}
		return (double) j / getSize();		
	}
	
	public double getBestValue(Node n) {
		double best = 0;
		 for (int i = 0; i < getSize(); i++) {
			 StateProbabilityPair pair = getPair(i);
			 Node pairnode = new Node(n, new IndexAction(i), pair.getState());
			 best += pair.getProbability() * pairnode.getBestValue();
		 }   
		 n.bestNode = n;
		 n.best = best;
		 return best;
	}

	@Override
	public State changeWeaponDurability(Hero target, int amount) {
		// TODO Auto-generated method stub
		return null;
	}

}
