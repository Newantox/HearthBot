package Game;

import java.util.ArrayList;
import java.util.Set;

import Game.Auras.Aura;
import Game.Battlecrys.Battlecry;
import Game.Buffs.Buff;
import Game.DeathEffects.DeathEffect;
import Game.Deathrattles.Deathrattle;
import Game.Heroes.Hero;
import Game.Inspires.Inspire;
import Game.Minions.Minion;
import Game.SummonEffects.SummonEffect;
import Game.Weapons.Weapon;
import Search.Action;
import Search.Node;

public class ChoiceState implements MyTurnState {
	
	private boolean turnEnded = false;

	private BoardState state;
	private Set<Action> actions;
	private BufferType bufferType;
	private int sourceId;
	
	public BoardState getState() {
		return state;
	}
	
	public boolean isTurnEnded() {
		return turnEnded;
	}

	public void setTurnEnded(boolean turnEnded) {
		this.turnEnded = turnEnded;
	}
	
	public ChoiceState() {
		
	}

	public ChoiceState(BoardState state, Set<Action> actions, BufferType bufferType, int sourceId) {
		this.state = state;
		this.actions = actions;
		this.bufferType = bufferType;
		this.sourceId = sourceId;
	}

	@Override
	public Set<Action> getApplicableActions() {
		return actions;
	}

	@Override
	public MyTurnState getActionResult(Action action) {
		System.out.println("Viewing result of choice");
		MyTurnState tempstate = action.result(state);
		if (bufferType.equals(BufferType.BATTLECRY)){
			tempstate = tempstate.applyAuras(sourceId);
			tempstate = tempstate.doSummonEffects(sourceId);
		}
		return tempstate;
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
	public MyTurnState applyAura(Aura aura, int sourceId, int targetId) {
		return state.applyAura(aura,sourceId,targetId);
	}
	
	@Override
	public MyTurnState applyAuras(int minionId) {
		return state.applyAuras(minionId);
	}
	
	@Override
	public MyTurnState removeAura(Aura aura, Minion source, int targetId) {
		return state.removeAura(aura,source,targetId);
	}
	
	@Override
	public MyTurnState removeAuras(Minion minion) {
		return state.removeAuras(minion);
	}
	
	@Override
	public MyTurnState doSummonEffect(SummonEffect summonEffect, int sourceId, int targetId, TargetsType side) {
		return state.doSummonEffect(summonEffect,sourceId,targetId,side);
	}
	
	@Override
	public MyTurnState doSummonEffects(int minionId) {
		return state.doSummonEffects(minionId);
	}
	
	@Override
	public MyTurnState doDeathEffect(DeathEffect deathEffect, int sourceId, Minion destroyedMinion) {
		return state.doDeathEffect(deathEffect,sourceId,destroyedMinion);
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
		return new ChoiceState((BoardState) state.viewBiased(), actions,bufferType,sourceId);
	}

	@Override
	public MyTurnState applyBuff(int minionID, Buff buff) {
		return state.applyBuff(minionID,buff);
	}

	@Override
	public MyTurnState removeBuff(int minionID, int id) {
		return state.removeBuff(minionID,id);
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
