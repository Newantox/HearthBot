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
		//for (int i = 0; i < states.size(); i++) {
		//	actions.add(new IndexAction(i));
		//}
		return actions;
	}

	@Override
	public MyTurnState getActionResult(Action action) {
		//IndexAction index = (IndexAction)action;
		//if (index==null) System.out.println("dssfv0");
		//return (states.get(index.getIndex())).getState();
		System.out.println("Doing action on random state");
		return null;
	}
	
	public int getSize() {
		return states.size();
	}
	
	public StateProbabilityPair getPair(int i) {
		return states.get(i);
	}
	
	@Override
	public MyTurnState placeMinion(Minion minion,int position) {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			list.add(new StateProbabilityPair(thing.getState().placeMinion(minion,position),thing.getProbability() , thing.getText()));
		}
		return new RandomState(list);
	}
	
	@Override
	public MyTurnState damage(Character character, int amount) {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			list.add(new StateProbabilityPair(thing.getState().damage(character,amount),thing.getProbability() , thing.getText()));
		}
		return new RandomState(list);
	}

	@Override
	public MyTurnState damageRandomHittable(TargetsType targets, int amount) {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			list.add(new StateProbabilityPair(thing.getState().damageRandomHittable(targets,amount),thing.getProbability() , thing.getText()));
		}
		return new RandomState(list);
	}
	
	@Override
	public MyTurnState simultaneousHeal(TargetsType targets, int amount, ArrayList<Minion> exceptions) {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			list.add(new StateProbabilityPair(thing.getState().simultaneousHeal(targets,amount,exceptions),thing.getProbability() , thing.getText()));
		}
		return new RandomState(list);
	}
	
	@Override
	public MyTurnState drawCard(int pos) {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			list.add(new StateProbabilityPair((thing.getState()).drawCard(pos),thing.getProbability() , thing.getText()));
		}
		return new RandomState(list);
	}
	
	@Override
	public MyTurnState drawCard() {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			list.add(new StateProbabilityPair((thing.getState()).drawCard(),thing.getProbability() , thing.getText()));
		}
		return new RandomState(list);
	}
	
	@Override
	public MyTurnState enemyDrawCard() {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			if (list.add(new StateProbabilityPair((thing.getState()).enemyDrawCard(),thing.getProbability() , thing.getText())));
		}
		return new RandomState(list);
	}
	
	@Override
	public MyTurnState changeHeroWeaponAtkDurability(int weaponID, int amountAtk, int amountDurability) {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			list.add(new StateProbabilityPair((thing.getState()).changeHeroWeaponAtkDurability(weaponID, amountAtk,amountDurability),thing.getProbability() , thing.getText()));
		}
		return new RandomState(list);
	}

	@Override
	public MyTurnState changeEnemyWeaponAtkDurability(int weaponID, int amountAtk, int amountDurability) {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			list.add(new StateProbabilityPair((thing.getState()).changeEnemyWeaponAtkDurability(weaponID, amountAtk,amountDurability),thing.getProbability() , thing.getText()));
		}
		return new RandomState(list);
	}
	
	@Override
	public MyTurnState performBC(Battlecry battlecry, PlayableCard card) {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			list.add(new StateProbabilityPair(battlecry.trigger(card,thing.getState()),thing.getProbability() , thing.getText()));
		}
		return new RandomState(list);
	}
	
	@Override
	public MyTurnState performDR(Deathrattle deathrattle, PlayableCard card, TargetsType side) {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			list.add(new StateProbabilityPair(deathrattle.trigger(thing.getState(),card,side),thing.getProbability() , thing.getText()));
		}
		return new RandomState(list);
	}
	
	@Override
	public MyTurnState performInspire(Inspire inspire, PlayableCard card) {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			list.add(new StateProbabilityPair(inspire.trigger(card,thing.getState()),thing.getProbability() , thing.getText()));
		}
		return new RandomState(list);
	}
	
	@Override
	public MyTurnState applyBuff(int minionID, String name, Buff buff) {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			list.add(new StateProbabilityPair((thing.getState()).applyBuff(minionID,name,buff),thing.getProbability() , thing.getText()));
		}
		return new RandomState(list);
	}
	
	@Override
	public MyTurnState removeBuff(int minionID, String name, int id) {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			list.add(new StateProbabilityPair((thing.getState()).removeBuff(minionID,name,id),thing.getProbability() , thing.getText()));
		}
		return new RandomState(list);
	}
	
	@Override
	public MyTurnState applyAuras(Minion minion) {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			list.add(new StateProbabilityPair((thing.getState()).applyAuras(minion),thing.getProbability() , thing.getText()));
		}
		return new RandomState(list);
	}
	
	@Override
	public MyTurnState removeAuras(Minion minion) {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			list.add(new StateProbabilityPair((thing.getState()).removeAuras(minion),thing.getProbability() , thing.getText()));
		}
		return new RandomState(list);
	}
	
	@Override
	public MyTurnState doSummonEffects(Minion minion) {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			list.add(new StateProbabilityPair((thing.getState()).doSummonEffects(minion),thing.getProbability() , thing.getText()));
		}
		return new RandomState(list);
	}
	
	@Override
	public MyTurnState doDeathEffects(Minion minion, TargetsType side) {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			list.add(new StateProbabilityPair((thing.getState()).doDeathEffects(minion,side),thing.getProbability() , thing.getText()));
		}
		return new RandomState(list);
	}
	
	@Override
	public MyTurnState doStartTurnEffects(Hero hero) {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			list.add(new StateProbabilityPair((thing.getState()).doStartTurnEffects(hero),thing.getProbability() , thing.getText()));
		}
		return new RandomState(list);
	}

	@Override
	public MyTurnState doEndTurnEffects(Hero hero) {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			list.add(new StateProbabilityPair((thing.getState()).doEndTurnEffects(hero),thing.getProbability() , thing.getText()));
		}
		return new RandomState(list);
	}
	
	@Override
	public MyTurnState heroAttack(int id, Hero defender) {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			list.add(new StateProbabilityPair((thing.getState()).heroAttack(id,defender),thing.getProbability() , thing.getText()));
		}
		return new RandomState(list);
	}

	@Override
	public MyTurnState heroAttack(int id, Minion defender) {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			list.add(new StateProbabilityPair((thing.getState()).heroAttack(id,defender),thing.getProbability() , thing.getText()));
		}
		return new RandomState(list);
	}
	
	@Override
	public MyTurnState giveWeapon(Hero hero, Weapon weapon) {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			list.add(new StateProbabilityPair(thing.getState().giveWeapon(hero,weapon),thing.getProbability() , thing.getText()));
		}
		return new RandomState(list);
	}
	
	@Override
	public MyTurnState equipHeroWeapon(Weapon weapon) {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			list.add(new StateProbabilityPair(thing.getState().equipHeroWeapon(weapon),thing.getProbability() , thing.getText()));
		}
		return new RandomState(list);
	}
	
	@Override
	public MyTurnState equipEnemyWeapon(Weapon weapon) {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			list.add(new StateProbabilityPair(thing.getState().equipEnemyWeapon(weapon),thing.getProbability() , thing.getText()));
		}
		return new RandomState(list);
	}
	
	@Override
	public MyTurnState removeTempEffects() {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			list.add(new StateProbabilityPair(thing.getState().removeTempEffects(),thing.getProbability() , thing.getText()));
		}
		return new RandomState(list);
	}
	
	@Override
	public MyTurnState endTurn(Hero hero) {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			list.add(new StateProbabilityPair(thing.getState().endTurn(hero),thing.getProbability() , thing.getText()));
		}
		return new RandomState(list);
	}
	
	@Override
	public MyTurnState addCardToMyHand(PlayableCard card) {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			list.add(new StateProbabilityPair(thing.getState().addCardToMyHand(card),thing.getProbability() , thing.getText()));
		}
		return new RandomState(list);
	}
	
	@Override
	public MyTurnState addCardToEnemyHand(PlayableCard card) {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		for (StateProbabilityPair thing : states) {
			list.add(new StateProbabilityPair(thing.getState().addCardToEnemyHand(card),thing.getProbability() , thing.getText()));
		}
		return new RandomState(list);
	}
	
	public MyTurnState resolveRNG(boolean print) {
		
		if (states.size()==1) return ((states.get(0)).getState()).resolveRNG(print);
		
		double random = Math.random();
		
		for (StateProbabilityPair pair : states) {
			if (random<=pair.getProbability()) {
				if (print) pair.print();
				return (pair.getState()).resolveRNG(print);
			}
			else random -= pair.getProbability();
		}
		if (print) (states.get(0)).print();
		return ((states.get(0)).getState()).resolveRNG(print);
	}
	
	public double getValue(Node n,double minionWeight, double hpWeight) {
		//System.out.println(getSize());
		int j = 0;
		for (int i = 0; i < getSize(); i++) { ///////
			StateProbabilityPair pair = getPair(i);
			Node node = new Node(n,new IndexAction(i),pair.getState().resolveRNG(false));
			j += pair.getProbability()*pair.getState().resolveRNG(false).getValue(node,minionWeight,hpWeight);
		}
		return (double) j / getSize();		
	}
	
	public double getBestValue(Node n, double minionWeight, double hpWeight) {
		double best = 0;
		 for (int i = 0; i < getSize(); i++) {
			 StateProbabilityPair pair = getPair(i);
			 Node pairnode = new Node(n, new IndexAction(i), pair.getState());
			 best += pair.getProbability() * pairnode.getBestValue(minionWeight,hpWeight);
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
	public String output() {
		String newline = System.getProperty("line.separator");
		String s = ("Possible states:");
		for (StateProbabilityPair pair : states) {
			s = s+newline;
			s = s+pair.getState().output();
			s = s+newline;
			s = s+("^ With probability "+pair.getProbability());
		}
		s = s+newline;
		s = s+("----------------");
		return s;
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
			newStates.add(new StateProbabilityPair((pair.getState()).viewBiased(), pair.getProbability() , pair.getText()));
		}
		return new RandomState(newStates);
	}

}
