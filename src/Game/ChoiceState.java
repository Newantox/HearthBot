package Game;

import java.util.Set;

import Game.Battlecrys.MinionBattlecry;
import Game.Battlecrys.WeaponBattlecry;
import Game.Deathrattles.MinionDeathrattle;
import Game.Deathrattles.WeaponDeathrattle;
import Game.Heroes.Hero;
import Game.Minions.Minion;
import Game.Weapons.Weapon;
import Search.Action;
import Search.Node;
import Search.State;

public class ChoiceState implements State {

	private BoardState state;
	private Set<Action> actions;
	
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
	public State damageRandomHittable(TargetsType targets, int amount) {
		return state.damageRandomHittable(targets, amount);
	}
	
	@Override
	public State drawCard() {
		return state.drawCard();
	}
	
	@Override
	public State changeWeaponDurability(Hero target, int amount) {
		return state.changeWeaponDurability(target, amount);
	}
	
	@Override
	public State placeMinion(Minion minion) {
		return state.placeMinion(minion);
	}
	
	@Override
	public State performBC(MinionBattlecry battlecry, Minion minion) {
		return state.performBC(battlecry, minion);
	}

	@Override
	public State performDR(MinionDeathrattle deathrattle, Minion minion) {
		return state.performDR(deathrattle, minion);
	}
	
	@Override
	public State performBC(WeaponBattlecry battlecry) {
		return state.performBC(battlecry);
	}
	
	@Override
	public State performDR(WeaponDeathrattle deathrattle) {
		return state.performDR(deathrattle);
	}
	
	@Override
	public State equipHeroWeapon(Weapon weapon) {
		return state.equipHeroWeapon(weapon);
	}
	
	@Override
	public State equipEnemyWeapon(Weapon weapon) {
		return state.equipEnemyWeapon(weapon);
	}
	
	public double getValue(Node n) {
		return state.getValue(n);
	}
	
	public double getBestValue(Node n) {
		System.out.println("Choice");
		double best = 1000;
		 for (Action action : getApplicableActions()) {
			 Node newnode = new Node(n,action,getActionResult(action));
			 if (newnode.getBestValue() < best) {
				 best = newnode.getBestValue();
				 n.bestNode = newnode;
				 n.best = best;
			 }
		  }
		 return best;
	}
	
}
