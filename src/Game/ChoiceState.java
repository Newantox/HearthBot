package Game;

import java.util.ArrayList;
import java.util.Set;

import Game.Battlecrys.Battlecry;
import Game.Buffs.Buff;
import Game.Deathrattles.Deathrattle;
import Game.Heroes.Hero;
import Game.Inspires.Inspire;
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
	public MyTurnState changeHeroWeaponAtkDurability(double id, int amountAtk, int amountDurability) {
		return state.changeHeroWeaponAtkDurability(id, amountAtk,amountDurability);
	}
	
	@Override
	public MyTurnState changeEnemyWeaponAtkDurability(double id, int amountAtk, int amountDurability) {
		return state.changeEnemyWeaponAtkDurability(id, amountAtk,amountDurability);
	}
	
	@Override
	public MyTurnState placeMinion(Minion minion) {
		return state.placeMinion(minion);
	}
	
	@Override
	public MyTurnState performBC(Battlecry battlecry, PlayableCard card) {
		return state.performBC(battlecry, card);
	}

	@Override
	public MyTurnState performDR(Deathrattle deathrattle, PlayableCard card) {
		return state.performDR(deathrattle, card);
	}
	
	@Override
	public MyTurnState performInspire(Inspire inspire, PlayableCard card) {
		return state.performInspire(inspire, card);
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
		return state.doStartTurnEffects(hero);
	}

	@Override
	public MyTurnState doEndTurnEffects(Hero hero) {
		return state.doEndTurnEffects(hero);
	}

	@Override
	public MyTurnState viewBiased() {
		return new ChoiceState((BoardState) state.viewBiased(), actions);
	}

	@Override
	public MyTurnState applyBuff(double minionID, Buff buff) {
		return state.applyBuff(minionID,buff);
	}

	@Override
	public MyTurnState removeBuff(double  minionID, double id) {
		return state.removeBuff(minionID,id);
	}

	@Override
	public MyTurnState damage(Character defender, int atk) {
		return state.damage(defender,atk);
	}

	@Override
	public MyTurnState heroAttack(double id, Hero defender) {
		return state.heroAttack(id, defender);
	}

	@Override
	public MyTurnState heroAttack(double id, Minion defender) {
		return state.heroAttack(id, defender);
	}

	@Override
	public MyTurnState simultaneousHeal(TargetsType enemyminions, int i, ArrayList<Minion> arrayList) {
		return state.simultaneousHeal(enemyminions, i, arrayList);
	}
	
}
