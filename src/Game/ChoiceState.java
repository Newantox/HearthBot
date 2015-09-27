package Game;

import java.util.Set;

import Game.Battlecrys.MinionBattlecry;
import Game.Battlecrys.WeaponBattlecry;
import Game.Deathrattles.MinionDeathrattle;
import Game.Deathrattles.WeaponDeathrattle;
import Game.Heroes.Hero;
import Game.Inspires.MinionInspire;
import Game.Inspires.WeaponInspire;
import Game.Minions.Minion;
import Game.Weapons.Weapon;
import Search.Action;
import Search.Node;

public class ChoiceState implements MyTurnState {
	
	private boolean turnEnded = false;

	private BoardState state;
	private Set<Action> actions;
	
	public BoardState getState() {
		return state;
	}
	
	public boolean isTurnEnded() {
		return turnEnded;
	}

	public void setTurnEnded(boolean turnEnded) {
		this.turnEnded = turnEnded;
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
	public MyTurnState getActionResult(Action action) {
		return action.result(state);
	}

	@Override
	public MyTurnState damageRandomHittable(TargetsType targets, int amount) {
		return state.damageRandomHittable(targets, amount);
	}
	
	@Override
	public MyTurnState drawCard(int pos) {
		return state.drawCard(pos);
	}
	
	@Override
	public MyTurnState drawCard() {
		return state.drawCard();
	}
	
	@Override
	public MyTurnState enemyDrawCard() {
		return state.enemyDrawCard();
	}
	
	@Override
	public MyTurnState changeHeroWeaponAtkDurability(int amountAtk, int amountDurability) {
		return state.changeHeroWeaponAtkDurability(amountAtk,amountDurability);
	}
	
	@Override
	public MyTurnState changeEnemyWeaponAtkDurability(int amountAtk, int amountDurability) {
		return state.changeEnemyWeaponAtkDurability(amountAtk,amountDurability);
	}
	
	@Override
	public MyTurnState placeMinion(Minion minion) {
		return state.placeMinion(minion);
	}
	
	@Override
	public MyTurnState changeAtkHP(Minion minion, int amountAtk, int amountHP) {
		return state.changeAtkHP(minion, amountAtk, amountHP);
	}
	
	@Override
	public MyTurnState changeAttributes(Minion minion, boolean charge, boolean divineshield, boolean taunt, boolean stealth, boolean windfury, int spelldamage, boolean frozen) {
		return state.changeAttributes(minion,charge,divineshield,taunt,stealth,windfury,spelldamage,frozen);
	}
	
	@Override
	public MyTurnState performBC(MinionBattlecry battlecry, Minion minion) {
		return state.performBC(battlecry, minion);
	}

	@Override
	public MyTurnState performDR(MinionDeathrattle deathrattle, Minion minion) {
		return state.performDR(deathrattle, minion);
	}
	
	@Override
	public MyTurnState performBC(WeaponBattlecry battlecry) {
		return state.performBC(battlecry);
	}
	
	@Override
	public MyTurnState performDR(WeaponDeathrattle deathrattle) {
		return state.performDR(deathrattle);
	}
	
	@Override
	public MyTurnState performInspire(MinionInspire inspire, Minion minion) {
		return state.performInspire(inspire, minion);
	}
	
	@Override
	public MyTurnState performInspire(WeaponInspire inspire) {
		return state.performInspire(inspire);
	}
	
	@Override
	public MyTurnState applyAuras(Minion minion) {
		return state.applyAuras(minion);
	}
	
	@Override
	public MyTurnState removeAuras(Minion minion) {
		return state.removeAuras(minion);
	}
	
	@Override
	public MyTurnState doSummonEffects(Minion minion) {
		return state.doSummonEffects(minion);
	}
	
	@Override
	public MyTurnState doDeathEffects(Minion minion) {
		return state.doDeathEffects(minion);
	}
	
	@Override
	public MyTurnState giveWeapon(Hero hero, Weapon weapon) {
		return state.giveWeapon(hero, weapon);
	}
	
	@Override
	public MyTurnState equipHeroWeapon(Weapon weapon) {
		return state.equipHeroWeapon(weapon);
	}
	
	@Override
	public MyTurnState equipEnemyWeapon(Weapon weapon) {
		return state.equipEnemyWeapon(weapon);
	}
	
	public MyTurnState resolveRNG() {
		return this;
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
	
	public boolean isGameWon() {
		return false;
	}

	@Override
	public void print() {
		state.print();
		System.out.println("Choice of:");
		for (Action action : actions) {
			action.print();
		}
		System.out.println("------------");
		
	}

	@Override
	public MyTurnState doStartTurnEffects(Hero hero) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyTurnState doEndTurnEffects(Hero hero) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyTurnState viewBiased() {
		return new ChoiceState((BoardState) state.viewBiased(), actions);
	}
	
}
