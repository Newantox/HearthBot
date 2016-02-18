package Game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.LinkedHashSet;

import Game.Actions.Attack;
import Game.Actions.EndTurn;
import Game.Actions.FaceAttack;
import Game.Actions.HeroAttack;
import Game.Actions.HeroFaceAttack;
import Game.Actions.PlayCard;
import Game.Auras.Aura;
import Game.Battlecrys.Battlecry;
import Game.Buffs.Buff;
import Game.Cards.Spells.TargettedSpell.TargettedSpell;
import Game.Character;
import Game.DeathEffects.DeathEffect;
import Game.Deathrattles.Deathrattle;
import Game.EndTurnEffects.EndTurnEffect;
import Game.Heroes.Hero;
import Game.Inspires.Inspire;
import Game.Minions.Minion;
import Game.StartTurnEffects.StartTurnEffect;
import Game.SummonEffects.SummonEffect;
import Game.Weapons.Weapon;
import Search.Action;
import Search.Node;

public class BoardState implements MyTurnState {
	
	//Tracker to remove action choices when EndTurn action is performed.
	private boolean  turnEnded;
	
	//ViewType tracker to determine whether enemy hand and secrets are "visible".
	private ViewType viewType;
	
	private Hero hero;
	private Hero enemy;
		
	private ArrayList<Minion> oppSide = new ArrayList<Minion>();
	private ArrayList<Minion> mySide = new ArrayList<Minion>();
	
	//Stores positions of minions, in order of the time they were played.
	private ArrayList<Integer> idsInPlayOrder;
	
	//Biased ViewType tracker, as substitute for updating enemy hand.
	private int enemyHandSize;
	
	private int idCounter;

	public BoardState(ViewType viewType, Hero hero, Hero enemy, ArrayList<Minion> oppSide, ArrayList<Minion> mySide, ArrayList<Integer> newIdsInPlayOrder, int enemyHandSize, boolean turnEnded, int idCounter) {
		this.viewType = viewType;
		this.hero = hero;
		this.enemy = enemy;
		this.oppSide = oppSide;
		this.mySide = mySide;
		this.idsInPlayOrder = newIdsInPlayOrder;
		this.enemyHandSize = enemyHandSize;
		this.turnEnded = turnEnded;
		this.idCounter = idCounter;
	}

	@Override
	public Set<Action> getApplicableActions() {
	
		if (turnEnded) return new LinkedHashSet<Action>();
		
		else {
			Set<Action> actions = new LinkedHashSet<Action>();
			
			//Add possible attacks by minions to the set of actions.
			for (Minion myMinion : mySide) {
				if (myMinion.canAttack()) {
					if (enemy.isAttackable()) actions.add(new FaceAttack(myMinion.getId(),enemy,findPosition(myMinion.getId())));
					for (Minion oppMinion : oppSide) {
						if (oppMinion.isAttackable(this) && oppMinion.isTargettable()) actions.add(new Attack(myMinion.getId(),oppMinion.getId()));
					}
				}
			}
			
			//Add possible attacks by hero to the set of actions.
			if (hero.canAttack() && hero.isReady()) {
				if (enemy.isAttackable()) actions.add(new HeroFaceAttack(hero,enemy));
				for (Minion oppMinion : oppSide) {
					if (oppMinion.isAttackable(this) && oppMinion.isTargettable()) actions.add(new HeroAttack(getHero(),oppMinion));
				}
			}
			
			//Add hero power to set of actions.
			if (hero.getHeroPower().useable(this) && !hero.getPowerUsed()) actions.add(hero.getHeroPower());
			
			//Add playing cards in hand to set of actions.
			for (int i = 0; i < (hero.getMyHandSize()); i++) {
				if (((hero.getMyHand()).raw()).get(i) != null) {
					PlayableCard card = ((hero.getMyHand()).raw()).get(i);
					if (card.getCost() <= hero.getCurrentMana()) { 
						if (card.getType().equals(CardType.MINION)){
							if (numberOfAlliedMinions()<7) {
								//No ability to select position to play minion yet, as adds too much complexity
								if (numberOfAlliedMinions()>0) actions.add(new PlayCard(card,mySide.get(numberOfAlliedMinions()-1),i));
								else actions.add(new PlayCard(card,null,i));
							}
						}
						else if (card.getType().equals(CardType.UNTARGETTEDSPELL)) {
							actions.add(new PlayCard(card,null,i));
						}
						else if (card.getType().equals(CardType.TARGETTEDSPELL)) {
							TargettedSpell tcard = (TargettedSpell) card;
							if (tcard.getTargets().equals(TargetsType.ALL)) {
								for (int id : idsInPlayOrder) {
									if (findMinion(id).isTargettable()) actions.add(new PlayCard(card,findMinion(id),i));
								}
								actions.add(new PlayCard(card,hero,i));
								actions.add(new PlayCard(card,enemy,i));
							}
							else if (tcard.getTargets().equals(TargetsType.ALLMINIONS)) {
								for (int id : idsInPlayOrder) {
									if (findMinion(id)==null) System.out.println("Couldn't find "+id);
									System.out.println("");
									if (findMinion(id).isTargettable()) actions.add(new PlayCard(card,findMinion(id),i));
								}
							}
						}
						else if (card.getType().equals(CardType.WEAPON)){
							actions.add(new PlayCard(card,null,i));
						}
					}
				}
				
			}
			//Add ability to end turn:
			actions.add(new EndTurn());
			return actions;
		}
	}

	@Override
	public MyTurnState getActionResult(Action action) {
		if (action==null) return this;
		return action.result(this);	
	}
	
	@Override 
	public boolean equals(Object that) {
		if (this == that) return true;
		else if (that == null) return false;
		else if (getClass() != that.getClass()) return false;
		else {
			final BoardState other = (BoardState) that;
			if (!hero.equals(other.getHero())) return false;
			if (!enemy.equals(other.getEnemy())) return false;
            else {
            	for (int i = 0; i<7; i++) {
            		if (oppSide.get(i) == null && (other.oppSide).get(i) != null) return false;
            		else if (oppSide.get(i) != null && (other.oppSide).get(i) == null) return false;
            		
            		if (oppSide.get(i) == null && (other.oppSide).get(i) == null);
            		else if (!(oppSide.get(i)).equals((other.oppSide).get(i))) return false;
            	}
            	for (int i = 0; i<7; i++) {
            		if (mySide.get(i) == null && (other.mySide).get(i) != null) return false;
            		else if (mySide.get(i) != null && (other.mySide).get(i) == null) return false;
            		
            		if (mySide.get(i) == null && (other.mySide).get(i) == null);
            		else if (!(mySide.get(i)).equals((other.mySide).get(i))) return false;
            	}
   
            	 return true;
            }
		}
}
		
	
	@Override
	public int hashCode() {
		int k = 0;
		for (int i = 0; i<7; i++) {
    		if (mySide.get(i) != null) k += (mySide.get(i)).getAtk() * (mySide.get(i)).getCurrentHP();
		}
		
		for (int i = 0; i<7; i++) {
    		if (oppSide.get(i) != null) k += (oppSide.get(i)).getAtk() * (oppSide.get(i)).getCurrentHP();
		}
		return k;
	}
	
	public ViewType getViewType() {
		return viewType;
	}

	public void setViewType(ViewType viewType) {
		this.viewType = viewType;
	}


	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}

	public Hero getEnemy() {
		return enemy;
	}

	public void setEnemy(Hero enemy) {
		this.enemy = enemy;
	}
	
	public ArrayList<Minion> getOppSide() {
		return oppSide;
	}

	public ArrayList<Minion> getMySide() {
		return mySide;
	}
	
	public ArrayList<Integer> getIdsInPlayOrder() {
		return idsInPlayOrder;
	}
	
	public int getEnemyHandSize() {
		return enemyHandSize;
	}

	public void setEnemyHandSize(int enemyHandSize) {
		this.enemyHandSize = enemyHandSize;
	}
	
	public int getTotalAlliedSpellDamage() {
		int k = 0;
		for (Minion minion : mySide) {
			k += minion.getSpelldamage();
		}
		return k;
	}
	
	public int getTotalEnemySpellDamage() {
		int k = 0;
		for (Minion minion : oppSide) {
			k += minion.getSpelldamage();
		}
		return k;
	}

	public List<Integer> getHittable(TargetsType targets) {
		List<Integer> list = new LinkedList<Integer>();
		
		switch (targets) {
			case ALLYCHAR:
				for (int i = 0; i<mySide.size(); i++) {
					list.add(i);
				}
				list.add(14);
				
			case ALLYMINIONS:
				for (int i = 0; i<mySide.size(); i++) {
					list.add(i);
				}
			
			case ENEMYCHAR:
				for (int i = 0; i<oppSide.size(); i++) {
					list.add(i+7);
				}
				list.add(15);
				
			case ENEMYMINIONS:
				for (int i = 0; i<oppSide.size(); i++) {
					list.add(i+7);
				}
				
		default:
			break;
		}
		
		return list;
	}
	
	public MyTurnState damageRandomHittable(TargetsType targets, int amount) {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		int possibilities = getHittable(targets).size();
		
		if (possibilities==0) return this;
		
		switch (targets) {
			case  ALLYCHAR:
				if (getHittable(TargetsType.ALLYCHAR).contains(7)) System.out.println("Ally contains 7");
				for (int j : getHittable(TargetsType.ALLYMINIONS)) {
					list.add(new StateProbabilityPair((mySide.get(j)).damage(this,amount) , (double)1 / possibilities , (mySide.get(j)).getName()+" gets damaged for "+amount));
				}
				list.add(new StateProbabilityPair(hero.damage(this,amount), (double)1 /possibilities , hero.getName()+" gets damaged for "+amount));
			
			case  ENEMYCHAR:
				for (int j : getHittable(TargetsType.ENEMYMINIONS)) {
					list.add(new StateProbabilityPair((oppSide.get(j-7)).damage(this,amount) , (double)1 / possibilities , (oppSide.get(j-7)).getName()+" gets damaged for "+amount));
				}
				list.add(new StateProbabilityPair(enemy.damage(this,amount), (double)1 /possibilities, enemy.getName()+" gets damaged for "+amount));
				
		default:
			break;
		}
		if (list.size()==1) return list.get(0).getState();
		else return new RandomState(list);
	}
	
	public MyTurnState damage(Character defender, int amount) {
		return defender.damage(this,amount);
	}
	
	public MyTurnState heal(Character defender, int amount) {
		return defender.heal(this,amount);
	}
	
	public MyTurnState spellDamageTarget(Character defender, int damage) {
		return damage(defender,damage+getTotalEnemySpellDamage());
	}
	
	public MyTurnState simultaneousDamage(TargetsType defenders, int amount, ArrayList<Minion> exceptions) {
		ArrayList<Hero> heroes = new ArrayList<Hero>();
		ArrayList<Minion> minions = new ArrayList<Minion>();
		ArrayList<Integer> amounts = new ArrayList<Integer>();
		
		if (defenders.equals(TargetsType.ALL)) {heroes.add(hero); heroes.add(enemy);}
		else if (defenders.equals(TargetsType.ALLYCHAR)) heroes.add(hero);
		else if (defenders.equals(TargetsType.ENEMYCHAR)) heroes.add(enemy);
		
		for (int id : idsInPlayOrder) {
			Minion minion = findMinion(id);
			if (!exceptions.contains(minion)) {
				if (findPosition(minion.getId())<7 && !(defenders.equals(TargetsType.ENEMYCHAR) || defenders.equals(TargetsType.ENEMYCHAR))) minions.add(minion);
				if (findPosition(minion.getId())>=7 && !(defenders.equals(TargetsType.ALLYCHAR) || defenders.equals(TargetsType.ALLYCHAR))) minions.add(minion);
			}
		}
		
		for (int i = 0; i < heroes.size()+minions.size(); i++) {
			amounts.add(amount);
		}
			
		return simultaneousDamage(heroes,minions,amounts);
		 
	}
	
	@SuppressWarnings("unchecked")
	public MyTurnState simultaneousDamage(ArrayList<Hero> heroes, ArrayList<Minion> minions, ArrayList<Integer> amounts) {
		ArrayList<Minion> destroys = new ArrayList<Minion>();
		
		Hero newHero = hero;
		Hero newEnemy = enemy;
		
		ArrayList<Minion> newMySide = (ArrayList<Minion>) mySide.clone();
		ArrayList<Minion> newOppSide = (ArrayList<Minion>) oppSide.clone();
		
		for (int j = 0; j<heroes.size(); j++) {
			if (!(heroes.get(j)).isImmune()) {
				Hero newChar = (heroes.get(j)).fresh();
				
				if (newChar.getArmour()>=amounts.get(j)) newChar.setArmour(newChar.getArmour()-amounts.get(j));
				else {int additional = amounts.get(j) - newChar.getArmour(); newChar.setArmour(0); newChar.setHP(newChar.getHP()-additional);}
			
			if (newChar.getSide().equals(TargetsType.ALLYCHAR)) newHero = newChar;
			else newEnemy = newChar;
			}
		}
		
		for (int j = 0; j<minions.size(); j++) {
			if (!(minions.get(j)).isImmune()) {
				Minion newMinion = new Minion(minions.get(j));
				
				if (newMinion.isDivineShield()) newMinion.setDivineShield(false);
				else if (newMinion.getDamageTaken() + amounts.get(j+heroes.size()) >= newMinion.getMaxHP()) destroys.add(newMinion);
				else newMinion.setDamageTaken(newMinion.getDamageTaken()+amounts.get(j+heroes.size()));
				
				if (findPosition(newMinion.getId())!=-1) {
					if (findPosition(newMinion.getId())<7) newMySide.set(findPosition(newMinion.getId()), newMinion);
					else newOppSide.set(findPosition(newMinion.getId())-7, newMinion);
				}
			}
				
		}
		BoardState tempstate = new BoardState(viewType,newHero,newEnemy,newOppSide,newMySide,idsInPlayOrder,enemyHandSize,turnEnded,idCounter);
		return tempstate.simultaneousDestroy(destroys);
	}
		
	public MyTurnState simultaneousSpellDamage(TargetsType defenders, int amount, ArrayList<Minion> exceptions) {
		return simultaneousDamage(defenders,amount+getTotalAlliedSpellDamage(),exceptions);	
	}
	
/*	public MyTurnState simultaneousSpellDamage(ArrayList<Hero> heroes, ArrayList<Minion> minions, ArrayList<Integer> amounts) {
		ArrayList<Integer> newAmounts = new ArrayList<Integer>();
		int k = getTotalAlliedSpellDamage();
		for (int n : amounts) newAmounts.add(n+k);
		return simultaneousDamage(heroes,minions,newAmounts);
	}*/
	
	@SuppressWarnings("unchecked")
	public MyTurnState simultaneousDestroy(ArrayList<Minion> minions) {
		ArrayList<Minion> newMySide = (ArrayList<Minion>) mySide.clone();
		ArrayList<Minion> newOppSide = (ArrayList<Minion>) oppSide.clone();
		
		ArrayList<Integer> newIdsInPlayOrder = (ArrayList<Integer>) idsInPlayOrder.clone();
		
		ArrayList<Integer> removedIds = new ArrayList<Integer>();
		
		for (Minion minion : minions) {
			removedIds.add(minion.getId());
		}
		
		ArrayList<Integer> removedIdsInOrder = (ArrayList<Integer>) idsInPlayOrder.clone();
		removedIdsInOrder.retainAll(removedIds);
		
		newIdsInPlayOrder.removeAll(removedIds);
		
		ArrayList<Minion> deathOrder = new ArrayList<Minion>();
		ArrayList<TargetsType> sideOrder = new ArrayList<TargetsType>();
		for (int id: removedIdsInOrder) {
			Minion minion = findMinion(id);
			deathOrder.add(minion);
			
			TargetsType side;
			if (findPosition(id)<7) side = TargetsType.ALLYCHAR;
			else side = TargetsType.ENEMYCHAR;
			
			sideOrder.add(side);
			
			if (findPosition(minion.getId())<7) newMySide.remove(minion);
			else newOppSide.remove(minion);	
		}
		
		MyTurnState tempstate = new BoardState(viewType,hero,enemy,newOppSide,newMySide,newIdsInPlayOrder,enemyHandSize,turnEnded,idCounter);
		
		for (Minion minion : deathOrder) {
			for (Deathrattle deathrattle : minion.getDeathrattles()) {
				tempstate = deathrattle.trigger(tempstate, minion, sideOrder.get(deathOrder.indexOf(minion)));
			}
		}
		return tempstate;
		
	}
	
	public MyTurnState simultaneousHeal(TargetsType defenders, int amount, ArrayList<Minion> exceptions) {
		ArrayList<Hero> heroes = new ArrayList<Hero>();
		ArrayList<Minion> minions = new ArrayList<Minion>();
		ArrayList<Integer> amounts = new ArrayList<Integer>();
		
		if (defenders.equals(TargetsType.ALL)) {heroes.add(hero); heroes.add(enemy);}
		else if (defenders.equals(TargetsType.ALLYCHAR)) heroes.add(hero);
		else if (defenders.equals(TargetsType.ENEMYCHAR)) heroes.add(enemy);
		
		for (int id : idsInPlayOrder) {
			Minion minion = findMinion(id);
			if (!exceptions.contains(minion)) {
				if (findPosition(minion.getId())<7 && !(defenders.equals(TargetsType.ENEMYCHAR) || defenders.equals(TargetsType.ENEMYCHAR))) minions.add(minion);
				if (findPosition(minion.getId())>=7 && !(defenders.equals(TargetsType.ALLYCHAR) || defenders.equals(TargetsType.ALLYCHAR))) minions.add(minion);
			}
		}
		
		for (int i = 0; i < heroes.size()+minions.size(); i++) {
			amounts.add(amount);
		}
			
		return simultaneousHeal(heroes,minions,amounts);
		 
	}
	
	@SuppressWarnings("unchecked")
	public MyTurnState simultaneousHeal(ArrayList<Hero> heroes , ArrayList<Minion> minions, ArrayList<Integer> amounts) {
		Minion newMinion;
		Hero newHero = hero;
		Hero newEnemy = enemy;
		
		ArrayList<Minion> newMySide = (ArrayList<Minion>) mySide.clone();
		ArrayList<Minion> newOppSide = (ArrayList<Minion>) oppSide.clone();
		
		for (int j = 0; j<heroes.size(); j++) {
			if (!(heroes.get(j)).isImmune()) {
				Hero newChar = (heroes.get(j)).fresh();
				
				if (newChar.getArmour()>=amounts.get(j)) newChar.setArmour(newChar.getArmour()-amounts.get(j));
				else {int additional = amounts.get(j) - newChar.getArmour(); newChar.setArmour(0); newChar.setHP(hero.getHP()-additional);}
			
			if (newChar.getSide().equals(TargetsType.ALLYCHAR)) newHero = newChar;
			else newEnemy = newChar;
			}
		}
		
		for (int j = 0; j<minions.size(); j++) {
			newMinion = new Minion(minions.get(j));
			
			if (newMinion.getDamageTaken() <= amounts.get(j)) newMinion.setDamageTaken(0);
			else newMinion.setDamageTaken(newMinion.getDamageTaken()-amounts.get(j));
			if (findPosition(newMinion.getId())<7) newMySide.set(findPosition(newMinion.getId()), newMinion);
			else newOppSide.set(findPosition(newMinion.getId())-7, newMinion);
		}
		return new BoardState(viewType,hero,enemy,newOppSide,newMySide,idsInPlayOrder,enemyHandSize,turnEnded,idCounter);
	}
	
	@SuppressWarnings("unchecked")
	public MyTurnState updateBoard(Minion minion) { 
		if (minion!=null) {
			if (findPosition(minion.getId())<7) {
				ArrayList<Minion> newMySide = (ArrayList<Minion>) mySide.clone();
		
				newMySide.set(findPosition(minion.getId()), minion);
				return new BoardState(viewType,hero,enemy,oppSide,newMySide,idsInPlayOrder,enemyHandSize,turnEnded,idCounter);
			}
			else {
				ArrayList<Minion> newOppSide = (ArrayList<Minion>) oppSide.clone();
		
				newOppSide.set(findPosition(minion.getId())-7, minion);
				return new BoardState(viewType,hero,enemy,newOppSide,mySide,idsInPlayOrder,enemyHandSize,turnEnded,idCounter);
			}
		}
		else return this;
	}
	
	public MyTurnState applyBuff(int minionID, Buff buff) {
		Minion minion;
		if (!(findMinion(minionID)==null)) {
			minion = findMinion(minionID);
			return updateBoard(minion.applyBuff(buff));
		}
		else return this;
	}
	
	public MyTurnState applyTempBuff(int minionID, Buff buff) {
		Minion minion;
		if (!findMinion(minionID).equals(null)) {
			minion = findMinion(minionID); 
			return updateBoard(minion.applyTempBuff(buff));
		}
		else return this;
	}
	
	public MyTurnState removeBuff(int minionID, int id) {
		Minion minion;
		if (!findMinion(minionID).equals(null)) {
			minion = findMinion(minionID); 
			return updateBoard(minion.removeBuff(id));
		}
		else return this;
	}
	
	public MyTurnState silence(int minionID) {
		if (!findMinion(minionID).equals(null)) {
			Minion minion = findMinion(minionID); 
			return minion.silence(this);
		}
		else return this;
	}
	
	public boolean inEffectRange(TargetsType relation, int applierId, TargetsType side) {
		switch (relation) {
			case ALLYMINIONS:
				if (findPosition(applierId)<7 && side.equals(TargetsType.ALLYCHAR) || findPosition(applierId)>=7 && side.equals(TargetsType.ENEMYCHAR)) return true;
				else return false;
				
			case ENEMYMINIONS:
				if (findPosition(applierId)<7 && side.equals(TargetsType.ENEMYCHAR) || findPosition(applierId)>=7 && side.equals(TargetsType.ALLYCHAR)) return true;
				else return false;
		
			default:
				return true;
		}
				
				
	}
	
	public MyTurnState applyAura(Aura aura, int sourceId, int targetId) {
		return aura.apply(this, findMinion(sourceId), findMinion(targetId));
	}
	
	public MyTurnState applyAuras(int summonedMinionId) {
		MyTurnState tempstate = this;
		Minion summonedMinionInit = findMinion(summonedMinionId);
	
		for (int id : idsInPlayOrder) {
				if (id!=summonedMinionId) {
					Minion minion = findMinion(id);
					
					TargetsType side;
					if (findPosition(id)<7) side = TargetsType.ALLYCHAR;
					else side = TargetsType.ENEMYCHAR;
					
					for (Aura aura : minion.getAuras()) {
						if (inEffectRange(aura.getEffectRange(),summonedMinionId,side)) tempstate = tempstate.applyAura(aura,minion.getId(),summonedMinionId);
					}
					for (Aura aura : summonedMinionInit.getAuras()) {
						if (inEffectRange(aura.getEffectRange(),summonedMinionId,side)) tempstate = tempstate.applyAura(aura,summonedMinionId,minion.getId());
					}
				}
		}
		return tempstate;
	}
	
	public MyTurnState removeAura(Aura aura, Minion source, int targetId) {
		return aura.remove(this, source, findMinion(targetId));
	}
	
	public MyTurnState removeAuras(Minion removedMinion) {
		MyTurnState tempstate = this;
		for (int id : idsInPlayOrder) {
				if (id!=removedMinion.getId()) {
					for (Aura aura : removedMinion.getAuras()) {
						tempstate = tempstate.removeAura(aura,removedMinion, id);
					}
				}
		}
		return tempstate;
	}
	
	public MyTurnState doSummonEffect(SummonEffect summonEffect, int sourceId, int summonedId, TargetsType side) {
		return summonEffect.perform(this,findMinion(sourceId),findMinion(summonedId),side);
	}
	
	public MyTurnState doSummonEffects(int summonedMinionId) {
		TargetsType side;
	
		if (findPosition(summonedMinionId)<7) side = TargetsType.ALLYCHAR;
		else side = TargetsType.ENEMYCHAR;
		
		MyTurnState tempstate = this;
		for (int id : idsInPlayOrder) {
				if (id!=summonedMinionId) {
					Minion minion = findMinion(id);
					for (SummonEffect summonEffect : minion.getSummonEffects()) {
						if (inEffectRange(summonEffect.getEffectRange(),id,side)) tempstate = tempstate.doSummonEffect(summonEffect,id,summonedMinionId,side);
					}
				}
		}
		return tempstate;
	}
	
	public MyTurnState doDeathEffect(DeathEffect deathEffect, int sourceId, Minion destroyedMinion) {
		return deathEffect.perform(this,findMinion(sourceId),destroyedMinion);
	}
	
	public MyTurnState doDeathEffects(Minion destroyedMinion, TargetsType side) {
		MyTurnState tempstate = this;
		for (int id : idsInPlayOrder) {
				if (id!=destroyedMinion.getId()) {
					Minion minion = findMinion(id);

					for (DeathEffect deathEffect : minion.getDeathEffects()) {
						if (inEffectRange(deathEffect.getEffectRange(),id,side)) tempstate = tempstate.doDeathEffect(deathEffect,id,destroyedMinion);
					}
				}
		}
		return tempstate;
	}
	
	public MyTurnState doStartTurnEffects(Hero startHero) {
		
		Hero newHero;
		if (startHero.getSide().equals(TargetsType.ALLYCHAR)) newHero = hero.fresh();
		else newHero = enemy.fresh();
		
		newHero.setCurrentMana(Math.min(10, newHero.getTotalMana()+1));
		newHero.setTotalMana(Math.min(10, newHero.getTotalMana()+1));
		newHero.setReady(true);
		
		MyTurnState tempstate;
		
		if (startHero.getSide().equals(TargetsType.ALLYCHAR)) {
		
			ArrayList<Minion> newMySide = new ArrayList<Minion>();
		
			for (Minion minion : mySide) {
				Minion newMinion = new Minion(minion);
				if (newMinion.isSummonSickness()) newMinion.setSummonSickness(false);
				newMinion.setAttacksTaken(0);
				newMySide.add(newMinion);
			}
		
			tempstate = new BoardState(viewType,newHero,enemy,oppSide,newMySide,idsInPlayOrder,enemyHandSize,turnEnded,idCounter);
			
		}
		else {
			
			ArrayList<Minion> newOppSide = new ArrayList<Minion>();
			
			for (Minion minion : oppSide) {
				Minion newMinion = new Minion(minion);
				if (newMinion.isSummonSickness()) newMinion.setSummonSickness(false);
				newMinion.setAttacksTaken(0);
				newOppSide.add(newMinion);
			}
			
			tempstate = new BoardState(viewType,hero,newHero,newOppSide,mySide,idsInPlayOrder,enemyHandSize,turnEnded,idCounter);
		
		}
		
		for (int id : idsInPlayOrder) {
			Minion minion = findMinion(id);
			
			for (StartTurnEffect startTurnEffect : minion.getStartTurnEffects()) {
					tempstate = startTurnEffect.perform(tempstate,hero);
			}
		}

		if (startHero.getSide().equals(TargetsType.ALLYCHAR)) tempstate =  tempstate.drawCard();
		else tempstate = tempstate.enemyDrawCard();
		return tempstate;
	}
	
	public MyTurnState endTurn(Hero hero) {
		MyTurnState tempstate = new BoardState(viewType,hero,enemy,oppSide,mySide,idsInPlayOrder,enemyHandSize,true,idCounter);
		return tempstate.doEndTurnEffects(hero);
	}
	
	public MyTurnState doEndTurnEffects(Hero hero) {
		MyTurnState tempstate = this;
		for (int id : idsInPlayOrder) {
			Minion minion = findMinion(id);
			if (minion==null) System.out.println(id + "is broken");
			for (EndTurnEffect endTurnEffect : minion.getEndTurnEffects()) {
					tempstate = endTurnEffect.perform(tempstate,hero.getSide(),minion);
			}
			
		}
	
		return tempstate.removeTempEffects();
	}
	
	@SuppressWarnings("unchecked")
	public MyTurnState removeTempEffects() {
		
		Hero newHero = hero.fresh();
		Hero newEnemy = enemy.fresh();
		
		ArrayList<Minion> newMySide = (ArrayList<Minion>) mySide.clone();
		ArrayList<Minion> newOppSide = (ArrayList<Minion>) oppSide.clone();
		
		for (int i = 0; i<mySide.size(); i++) {
			newMySide.set(i, mySide.get(i).removeTempBuffs());
		}
		
		for (int i = 0; i<oppSide.size(); i++) {
			newOppSide.set(i, oppSide.get(i).removeTempBuffs());
		}
		
		return new BoardState(viewType,newHero,newEnemy,newOppSide,newMySide,idsInPlayOrder,enemyHandSize,turnEnded,idCounter);
	}
	
	public MyTurnState heroAttack(int weaponID, Hero defender) {
		if (hero.getWeapon().getId()==weaponID) {
			Hero newHero = hero.fresh();
			newHero.setReady(false);
			
			MyTurnState tempstate = new BoardState(viewType,newHero,enemy,oppSide,mySide,idsInPlayOrder,enemyHandSize,turnEnded,idCounter);
			tempstate = tempstate.damage(defender,hero.getWeapon().getAtk());
			
			return tempstate.changeHeroWeaponAtkDurability(weaponID,0,-1);
		}
		else return this;
	}
	
	public MyTurnState heroAttack(int weaponID, Minion defender) {
		if (hero.getWeapon().getId()==weaponID && !findMinion(defender.getId()).equals(null)) {
			Minion target = findMinion(defender.getId());
			Hero newHero = hero.fresh();
			newHero.setReady(false);
			
			MyTurnState tempstate = new BoardState(viewType,newHero,enemy,oppSide,mySide,idsInPlayOrder,enemyHandSize,turnEnded,idCounter);
			tempstate = tempstate.damage(newHero,target.getAtk());
			tempstate = tempstate.damage(target,hero.getWeapon().getAtk());
			
			return tempstate.changeHeroWeaponAtkDurability(weaponID,0,-1);
		}
		else return this;
	}
	
	public MyTurnState changeHeroWeaponAtkDurability(int id, int amountAtk, int amountDurability) {
		if (hero.getWeapon().getId()==id) {
			Hero newTarget = hero.fresh();
			Weapon weapon = (hero.getWeapon()).fresh();
			weapon.setAtk(weapon.getAtk() + amountAtk);
			weapon.setDurability(weapon.getDurability() + amountDurability);
			if (weapon.getAtk()<=0) return newTarget.destroyWeapon(this);
			else if (weapon.getDurability()<=0) return newTarget.destroyWeapon(this);
			else {
				newTarget.setWeapon(weapon);
				return new BoardState(viewType,newTarget,enemy,oppSide,mySide,idsInPlayOrder,enemyHandSize,turnEnded,idCounter);
			}
		}
		else return this;
}
	
	public MyTurnState changeEnemyWeaponAtkDurability(int id, int amountAtk, int amountDurability) {
			if (enemy.getWeapon().getId()==id) {
				Hero newTarget = enemy.fresh();
				Weapon weapon = (enemy.getWeapon()).fresh();
				weapon.setAtk(weapon.getAtk() + amountAtk);
				weapon.setDurability(weapon.getDurability() + amountDurability);
				if (weapon.getAtk()<=0) return newTarget.destroyWeapon(this);
				else if (weapon.getDurability()<=0) return newTarget.destroyWeapon(this);
				else {
					newTarget.setWeapon(weapon);
					return new BoardState(viewType,hero,newTarget,oppSide,mySide,idsInPlayOrder,enemyHandSize,turnEnded,idCounter);
				}
			}
			else return this;
	}
	
	@Override
	public MyTurnState drawCard(int pos) {
		return hero.drawCard(this,pos);
	}
	
	@Override
	public MyTurnState drawCard() {
		return hero.drawCard(this);
	}
	
	public MyTurnState enemyDrawCard() {
		if (viewType==ViewType.UNBIASED) return enemy.drawCard(this);
		else {
			return new BoardState(viewType,hero,enemy,oppSide,mySide,idsInPlayOrder,enemyHandSize+1,turnEnded,idCounter);
		}
	}
	
	public MyTurnState addCardToMyHand(PlayableCard card) {
		if (hero.getMyHandSize()<10) {
			Hero newHero = hero.fresh();
			Hand newHand = new Hand(hero.getMyHand().raw());

			newHand = newHand.add(card);
			newHero.setMyHand(newHand);
			
			return new BoardState(viewType,newHero,enemy,oppSide,mySide,idsInPlayOrder,enemyHandSize,turnEnded,idCounter);
		}
		else return this;
	}
	
	public MyTurnState addCardToEnemyHand(PlayableCard card) {
		if (enemy.getMyHandSize()<10) {
			Hero newEnemy = enemy.fresh();
			Hand newHand = new Hand(enemy.getMyHand().raw());
			newHand = newHand.add(card);
			newEnemy.setMyHand(newHand);
		
			return new BoardState(viewType,hero,newEnemy,oppSide,mySide,idsInPlayOrder,enemyHandSize,turnEnded,idCounter);
		}
		else return this;
	}
	
	public int numberOfAlliedMinions() {
		return mySide.size();
	}
	
	public int numberOfEnemyMinions() {
		return oppSide.size();
	}
	
	public int numberOfMinions() {
		return mySide.size() + oppSide.size();
	}
	
	public boolean isTauntOnSide(ArrayList<Minion> side) {
		for (Minion minion : side) {
			if (minion.isTaunt()) return true;
		}
		return false;
	}
	
	@Override
	public MyTurnState placeMinion(Minion minion, int position) {
		return minion.place(this,position);
	}
	
	public BoardState resolveRNG(boolean b) {
		return this;
	}
	
	public double getValue(Node n, double hpWeight, double minionWeight) {
		if (enemy.getHP()<=0) return 0;
		
		if (turnEnded) {
			int k = 20;
			for (Minion minion : oppSide) {
				if (minion.isDivineShield()) k += 3.2*minionWeight*minion.getCurrentHP();
				else k += 2*minionWeight*minion.getCurrentHP();
				k += 1.6*minionWeight*minion.getAtk();
			}
			k += 0.2*hpWeight*enemy.getHP();
			if (enemy.getWeapon()!=null) k += 0.8*(enemy.getWeapon()).getAtk()*(enemy.getWeapon()).getDurability();
			k += 0.5*enemy.getMyHandSize();
			
			for (Minion minion : mySide) {
				if (minion.isDivineShield()) k -= 1.6*minionWeight*minion.getCurrentHP();
				else k -= minionWeight*minion.getCurrentHP();
				k -= 0.8*minionWeight*minion.getAtk();
			}
			k -= 0.05*hpWeight*hero.getHP();
			if (hero.getWeapon()!=null) 	k -= 0.4*(hero.getWeapon()).getAtk()*(hero.getWeapon()).getDurability();
			for (PlayableCard card : hero.getMyHand().raw()) {
				k -= 0.3*card.getCost();
			}
			
			return k;
		}
		else return (this.getActionResult(new EndTurn())).getValue(n,minionWeight,hpWeight);
	}
	
	public double getBestValue(Node n,double minionWeight, double hpWeight) {
		 double best = getValue(n,minionWeight,hpWeight);
		 if (best==0 || turnEnded) {
			 n.bestNode = n;
			 n.best = best;
			 return best;
		 }
		 else {
			Node newNode = new Node(n, new EndTurn(), getActionResult(new EndTurn()));
			n.bestNode = newNode;
			n.best = newNode.getBestValue(minionWeight,hpWeight);
			
			for (Action action : getApplicableActions()) {
				Node newnode = new Node(n, action, getActionResult(action));
				double nodebest = newnode.getBestValue(minionWeight,hpWeight);
				if (nodebest < best) {
					best = nodebest; 
					n.bestNode = newnode;
				 	n.best = best;
				}
			}
		 }
		 return best;
	}
	
	@Override
	public MyTurnState performBC(Battlecry battlecry, PlayableCard card) {
		return battlecry.perform(card,this);
	}

	@Override
	public MyTurnState performDR(Deathrattle deathrattle, PlayableCard card, TargetsType side) {
		return deathrattle.perform(this,card,side);
	}
	
	@Override
	public MyTurnState performInspire(Inspire inspire, PlayableCard card) {
		return inspire.perform(card,this);
	}
	
	@Override
	public MyTurnState giveWeapon(Hero hero, Weapon weapon) {
		return hero.giveWeapon(this, weapon);
	}
	
	@Override
	public MyTurnState equipHeroWeapon(Weapon weapon) {
		return hero.equipWeapon(this, weapon);
	}
	
	@Override
	public MyTurnState equipEnemyWeapon(Weapon weapon) {
		return enemy.equipWeapon(this, weapon);
	}
	
	public boolean isGameWon() {
		return enemy.getHP()<=0;
	}
	
	public void print() {
		System.out.println("Turn ended: "+turnEnded);
		System.out.println("          "+enemy.getName() + " (HP : "+enemy.getHP()+"/"+enemy.getMaxHP()+")" + " (Mana: "+enemy.getCurrentMana()+"/"+enemy.getTotalMana()+")" + " (Hand: "+enemy.getMyHandSize()+")" + " (Deck: "+(enemy.getMyDeck()).getSize()+")");
		System.out.println("");
		for (Minion minion : oppSide) {
			System.out.print(minion.getName() +"("+minion.getAtk()+"/"+minion.getCurrentHP()+")["+minion.getId()+"]   ");
		}
		System.out.println("");
		System.out.println("");
		for (Minion minion : mySide) {
			System.out.print(minion.getName() + "("+minion.getAtk()+"/"+minion.getCurrentHP()+")["+minion.getId()+"]   ");
		}
		System.out.println("");
		System.out.println("");
		System.out.println("          "+hero.getName() + " (HP : "+hero.getHP()+"/"+hero.getMaxHP()+")" + " (Mana: "+hero.getCurrentMana()+"/"+hero.getTotalMana()+")" + " (Hand: "+hero.getMyHandSize()+")" + " (Deck: "+(hero.getMyDeck()).getSize()+")");
		(hero.getMyHand()).print();
	}
	
	public String output() {
		String s = "<html><center>";
		s = s+("          "+enemy.getName() + " (HP : "+enemy.getHP()+"/"+enemy.getMaxHP()+")" + " (Mana: "+enemy.getCurrentMana()+"/"+enemy.getTotalMana()+")" + " (Hand: "+enemy.getMyHandSize()+")" + " (Deck: "+(enemy.getMyDeck()).getSize()+")");
		s = s+"<br>";
		s = s+"<br>";
		s = s+"<br>";
		for (Minion minion : oppSide) {
			s = s+(minion.getName() +"("+minion.getAtk()+"/"+minion.getCurrentHP()+")["+minion.getId()+"]   ");
		}
		s = s+"<br>";
		s = s+"<br>";
		s = s+"<br>";
		s = s+"<br>";
		for (Minion minion : mySide) {
			s = s+(minion.getName() + "("+minion.getAtk()+"/"+minion.getCurrentHP()+")["+minion.getId()+"]   ");
		}
		s = s+"<br>";
		s = s+"<br>";
		s = s+"<br>";
		s = s+("          "+hero.getName() + " (HP : "+hero.getHP()+"/"+hero.getMaxHP()+")" + " (Mana: "+hero.getCurrentMana()+"/"+hero.getTotalMana()+")" + " (Hand: "+hero.getMyHandSize()+")" + " (Deck: "+(hero.getMyDeck()).getSize()+")");
		s = s+"<br>";
		s = s+"<br>";
		s = s+(hero.getMyHand()).output();
		return s+"</center></html>";
	}

	public boolean isTurnEnded() {
		return turnEnded;
	}

	public void setTurnEnded(boolean turnEnded) {
		this.turnEnded = turnEnded;
	}

	@Override
	public MyTurnState viewBiased() {
		return new BoardState(ViewType.BIASED, hero, enemy, oppSide, mySide, idsInPlayOrder, enemyHandSize,turnEnded,idCounter);
	}
	
	public int getNextId() {
		return idCounter+1;
	}
	
	public int getIdCounter() {
		return idCounter;
	}
	
	public Minion findMinion(int minionID) {
		for (Minion minion : mySide) {
			if (minion.getId()==minionID) return new Minion(minion);
		}
		for (Minion minion : oppSide) {
			if (minion.getId()==minionID) return new Minion(minion);
		}
		return null;
	}
	
	public int findPosition(int minionID) {
		
		for (int i = 0; i<mySide.size(); i++) {
			if (mySide.get(i).getId()==minionID) return i;
		}
		for (int i = 0; i<oppSide.size(); i++) {
			if (oppSide.get(i).getId()==minionID) return i+7;
		}
		
		throw new Error(output()+"Couldn't find minion position for ID: "+minionID);
	}
}
