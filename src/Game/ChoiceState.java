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
	public MyTurnState changeHeroWeaponAtkDurability(int id, int amountAtk, int amountDurability) {
		return state.changeHeroWeaponAtkDurability(id, amountAtk,amountDurability);
	}
	
	@Override
	public MyTurnState changeEnemyWeaponAtkDurability(int id, int amountAtk, int amountDurability) {
		return state.changeEnemyWeaponAtkDurability(id, amountAtk,amountDurability);
	}
	
	@Override
	public MyTurnState placeMinion(Minion minion, int position) {
		return state.placeMinion(minion, position);
	}
	
	@Override
	public MyTurnState performBC(Battlecry battlecry, PlayableCard card) {
		return state.performBC(battlecry, card);
	}

	@Override
	public MyTurnState performDR(Deathrattle deathrattle, PlayableCard card, TargetsType side) {
		return state.performDR(deathrattle, card, side);
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
	public MyTurnState doDeathEffects(Minion minion, TargetsType side) {
		return state.doDeathEffects(minion,side);
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
	
	public MyTurnState resolveRNG(boolean b) {
		return this;
	}
	
	public double getValue(Node n,double minionWeight, double hpWeight) {
		return state.getValue(n,minionWeight,hpWeight);
	}
	
	public double getBestValue(Node n, double minionWeight, double hpWeight) {
		System.out.println("Choice");
		double best = 1000;
		 for (Action action : getApplicableActions()) {
			 Node newnode = new Node(n,action,getActionResult(action));
			 if (newnode.getBestValue(minionWeight,hpWeight) < best) {
				 best = newnode.getBestValue(minionWeight,hpWeight);
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
	public String output() {
		String s = "<html><br>"+state.output();
		s = s+"<br>";
		s = s+"<br>";
		s = s+("Choice of:");
		for (Action action : actions) {
			s = s+"<br>";
			s = s+"<br>";
			s = s+action.output();
		}
		s = s+"<br>";
		s = s+"<br>";
		s = s+("------------");
		return s+"</html>";
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
	public MyTurnState applyBuff(int minionID, String name, Buff buff) {
		return state.applyBuff(minionID,name,buff);
	}

	@Override
	public MyTurnState removeBuff(int minionID, String name, int id) {
		return state.removeBuff(minionID,name,id);
	}

	@Override
	public MyTurnState damage(Character defender, int atk) {
		return state.damage(defender,atk);
	}

	@Override
	public MyTurnState heroAttack(int id, Hero defender) {
		return state.heroAttack(id, defender);
	}

	@Override
	public MyTurnState heroAttack(int id, Minion defender) {
		return state.heroAttack(id, defender);
	}

	@Override
	public MyTurnState simultaneousHeal(TargetsType enemyminions, int i, ArrayList<Minion> arrayList) {
		return state.simultaneousHeal(enemyminions, i, arrayList);
	}

	@Override
	public MyTurnState removeTempEffects() {
		return state.removeTempEffects();
	}

	@Override
	public MyTurnState endTurn(Hero hero) {
		return state.endTurn(hero);
	}

	@Override
	public MyTurnState addCardToMyHand(PlayableCard card) {
		return state.addCardToMyHand(card);
	}
	
	@Override
	public MyTurnState addCardToEnemyHand(PlayableCard card) {
		return state.addCardToEnemyHand(card);
	}
	
}
