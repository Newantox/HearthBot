package Game;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import Game.Battlecrys.Battlecry;
import Game.Buffs.Buff;
import Game.Character;
import Game.Deathrattles.Deathrattle;
import Game.Heroes.Hero;
import Game.Inspires.Inspire;
import Game.Minions.Minion;
import Game.Weapons.Weapon;
import Search.Action;
import Search.Node;

public class RandomState implements MyTurnState {
	
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
	public MyTurnState getActionResult(Action action) {
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
	public MyTurnState placeMinion(Minion minion) {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			list.add(new StateProbabilityPair(thing.getState().placeMinion(minion),thing.getProbability()));
		}
		return new RandomState(list);
	}
	
	@Override
	public MyTurnState damage(Character character, int amount) {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			list.add(new StateProbabilityPair(thing.getState().damage(character,amount),thing.getProbability()));
		}
		return new RandomState(list);
	}

	@Override
	public MyTurnState damageRandomHittable(TargetsType targets, int amount) {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			list.add(new StateProbabilityPair(thing.getState().damageRandomHittable(targets,amount),thing.getProbability()));
		}
		return new RandomState(list);
	}
	
	@Override
	public MyTurnState simultaneousHeal(TargetsType targets, int amount, ArrayList<Minion> exceptions) {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			list.add(new StateProbabilityPair(thing.getState().simultaneousHeal(targets,amount,exceptions),thing.getProbability()));
		}
		return new RandomState(list);
	}
	
	@Override
	public MyTurnState drawCard(int pos) {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			list.add(new StateProbabilityPair((thing.getState()).drawCard(pos),thing.getProbability()));
		}
		return new RandomState(list);
	}
	
	@Override
	public MyTurnState drawCard() {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			list.add(new StateProbabilityPair((thing.getState()).drawCard(),thing.getProbability()));
		}
		return new RandomState(list);
	}
	
	@Override
	public MyTurnState enemyDrawCard() {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			if (list.add(new StateProbabilityPair((thing.getState()).enemyDrawCard(),thing.getProbability())));
		}
		return new RandomState(list);
	}
	
	@Override
	public MyTurnState changeHeroWeaponAtkDurability(double weaponID, int amountAtk, int amountDurability) {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			list.add(new StateProbabilityPair((thing.getState()).changeHeroWeaponAtkDurability(weaponID, amountAtk,amountDurability),thing.getProbability()));
		}
		return new RandomState(list);
	}

	@Override
	public MyTurnState changeEnemyWeaponAtkDurability(double weaponID, int amountAtk, int amountDurability) {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			list.add(new StateProbabilityPair((thing.getState()).changeEnemyWeaponAtkDurability(weaponID, amountAtk,amountDurability),thing.getProbability()));
		}
		return new RandomState(list);
	}
	
	@Override
	public MyTurnState performBC(Battlecry battlecry, PlayableCard card) {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			list.add(new StateProbabilityPair(battlecry.trigger(card,thing.getState()),thing.getProbability()));
		}
		return new RandomState(list);
	}
	
	@Override
	public MyTurnState performDR(Deathrattle deathrattle, PlayableCard card) {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			list.add(new StateProbabilityPair(deathrattle.trigger(card,thing.getState()),thing.getProbability()));
		}
		return new RandomState(list);
	}
	
	@Override
	public MyTurnState performInspire(Inspire inspire, PlayableCard card) {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			list.add(new StateProbabilityPair(inspire.trigger(card,thing.getState()),thing.getProbability()));
		}
		return new RandomState(list);
	}
	
	@Override
	public MyTurnState applyBuff(double minionID, Buff buff) {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			list.add(new StateProbabilityPair((thing.getState()).applyBuff(minionID,buff),thing.getProbability()));
		}
		return new RandomState(list);
	}
	
	@Override
	public MyTurnState removeBuff(double minionID, double id) {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			list.add(new StateProbabilityPair((thing.getState()).removeBuff(minionID,id),thing.getProbability()));
		}
		return new RandomState(list);
	}
	
	@Override
	public MyTurnState applyAuras(Minion minion) {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			list.add(new StateProbabilityPair((thing.getState()).applyAuras(minion),thing.getProbability()));
		}
		return new RandomState(list);
	}
	
	@Override
	public MyTurnState removeAuras(Minion minion) {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			list.add(new StateProbabilityPair((thing.getState()).removeAuras(minion),thing.getProbability()));
		}
		return new RandomState(list);
	}
	
	@Override
	public MyTurnState doSummonEffects(Minion minion) {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			list.add(new StateProbabilityPair((thing.getState()).doSummonEffects(minion),thing.getProbability()));
		}
		return new RandomState(list);
	}
	
	@Override
	public MyTurnState doDeathEffects(Minion minion) {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			list.add(new StateProbabilityPair((thing.getState()).doDeathEffects(minion),thing.getProbability()));
		}
		return new RandomState(list);
	}
	
	@Override
	public MyTurnState doStartTurnEffects(Hero hero) {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			list.add(new StateProbabilityPair((thing.getState()).doStartTurnEffects(hero),thing.getProbability()));
		}
		return new RandomState(list);
	}

	@Override
	public MyTurnState doEndTurnEffects(Hero hero) {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			list.add(new StateProbabilityPair((thing.getState()).doEndTurnEffects(hero),thing.getProbability()));
		}
		return new RandomState(list);
	}
	
	@Override
	public MyTurnState heroAttack(double id, Hero defender) {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			list.add(new StateProbabilityPair((thing.getState()).heroAttack(id,defender),thing.getProbability()));
		}
		return new RandomState(list);
	}

	@Override
	public MyTurnState heroAttack(double id, Minion defender) {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			list.add(new StateProbabilityPair((thing.getState()).heroAttack(id,defender),thing.getProbability()));
		}
		return new RandomState(list);
	}
	
	@Override
	public MyTurnState giveWeapon(Hero hero, Weapon weapon) {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			list.add(new StateProbabilityPair(thing.getState().giveWeapon(hero,weapon),thing.getProbability()));
		}
		return new RandomState(list);
	}
	
	@Override
	public MyTurnState equipHeroWeapon(Weapon weapon) {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			list.add(new StateProbabilityPair(thing.getState().equipHeroWeapon(weapon),thing.getProbability()));
		}
		return new RandomState(list);
	}
	
	@Override
	public MyTurnState equipEnemyWeapon(Weapon weapon) {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			list.add(new StateProbabilityPair(thing.getState().equipEnemyWeapon(weapon),thing.getProbability()));
		}
		return new RandomState(list);
	}
	
	public MyTurnState resolveRNG() {
		
		if (states.size()==1) return ((states.get(0)).getState()).resolveRNG();
		
		double random = Math.random();
		
		for (StateProbabilityPair pair : states) {
			if (random<=pair.getProbability()) return (pair.getState()).resolveRNG();
			else random -= pair.getProbability();
		}
		return ((states.get(0)).getState()).resolveRNG();
	}
	
	public double getValue(Node n) {
		int j = 0;
		for (int i = 0; i < getSize(); i++) {
			StateProbabilityPair pair = getPair(i);
			Node node = new Node(n,new IndexAction(i),pair.getState());
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
	
	public boolean isGameWon() {
		 for (int i = 0; i < getSize(); i++) {
			 StateProbabilityPair pair = getPair(i);
			 if (!(pair.getState()).isGameWon()) return false;
		 } 
		 return true;
	}

	@Override
	public void print() {
		System.out.println("Possible states:");
		for (StateProbabilityPair pair : states) {
			pair.getState().print();
			System.out.println("^ With probability "+pair.getProbability());
		}
		System.out.println("----------------");
	}

	@Override
	public boolean isTurnEnded() {
		for (int i = 0; i < getSize(); i++) {
			 StateProbabilityPair pair = getPair(i);
			 if (!(pair.getState()).isTurnEnded()) return false;
		 } 
		 return true;
	}

	@Override
	public void setTurnEnded(boolean b) {	
	}

	@Override
	public MyTurnState viewBiased() {
		List<StateProbabilityPair> newStates = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair pair : states) {
			newStates.add(new StateProbabilityPair((pair.getState()).viewBiased(), pair.getProbability()));
		}
		return new RandomState(newStates);
	}

}
