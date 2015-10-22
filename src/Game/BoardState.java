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
	private boolean  turnEnded = false;
	
	//ViewType tracker to determine whether enemy hand and secrets are "visible".
	private ViewType viewType;
	
	private Hero hero;
	private Hero enemy;
		
	private ArrayList<Minion> oppSide = new ArrayList<Minion>();
	private ArrayList<Minion> mySide = new ArrayList<Minion>();
	
	//Stores positions of minions, in order of the time they were played.
	private ArrayList<Double> idsInPlayOrder;
	
	//Biased ViewType tracker, as substitute for updating enemy hand.
	private int enemyHandSize;

	public BoardState(ViewType viewType, Hero hero, Hero enemy, ArrayList<Minion> oppSide, ArrayList<Minion> mySide, ArrayList<Double> idsInPlayOrder, int enemyHandSize) {
		this.viewType = viewType;
		this.hero = hero;
		this.enemy = enemy;
		this.oppSide = oppSide;
		this.mySide = mySide;
		this.idsInPlayOrder = idsInPlayOrder;
		this.enemyHandSize = enemyHandSize;
	}

	@Override
	public Set<Action> getApplicableActions() {
		Set<Action> actions = new LinkedHashSet<Action>();
		
		//Add possible attacks by minions to the set of actions.
		for (Minion myMinion : mySide) {
			if (myMinion.canAttack()) {
				if (enemy.isAttackable()) actions.add(new FaceAttack(myMinion,enemy));
				for (Minion oppMinion : oppSide) {
					if (oppMinion.isAttackable(this) && oppMinion.isTargettable()) actions.add(new Attack(myMinion,oppMinion));
				}
			}
		}
		
		//Add possible attacks by hero to the set of actions.
		if (hero.canAttack()) {
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
							else actions.add(new PlayCard(card,hero,i));
						}
					}
					else if (card.getType().equals(CardType.UNTARGETTEDSPELL)) {
						actions.add(new PlayCard(card,null,i));
					}
					else if (card.getType().equals(CardType.TARGETTEDSPELL)) {
						TargettedSpell tcard = (TargettedSpell) card;
						if (tcard.getTargets().equals(TargetsType.ALL)) {
							for (int position : positionsInPlayOrder) {
								if (position<7) actions.add(new PlayCard(card,mySide.get(position),i));
								else actions.add(new PlayCard(card,oppSide.get(position-7),i));
							}
							actions.add(new PlayCard(card,hero,i));
							actions.add(new PlayCard(card,enemy,i));
						}
						else if (tcard.getTargets().equals(TargetsType.ALLMINIONS)) {
							for (int position : positionsInPlayOrder) {
								if (position<7) actions.add(new PlayCard(card,mySide.get(position),i));
								else actions.add(new PlayCard(card,oppSide.get(position-7),i));
							}
						}
					}
					else if (card.getType().equals(CardType.WEAPON)){
						actions.add(new PlayCard(card,null,i));
					}
				}
			}
			
			//Add ability to end turn:
			actions.add(new EndTurn());
			
		}
		return actions;
	}

	@Override
	public MyTurnState getActionResult(Action action) {
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
	
	public ArrayList<Integer> getPositionsInPlayOrder() {
		return positionsInPlayOrder;
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
					if (mySide.get(i)!=null) list.add(i);
				}
				list.add(14);
				
			case ALLYMINIONS:
				for (int i = 0; i<mySide.size(); i++) {
					if (mySide.get(i)!=null) list.add(i);
				}
			
			case ENEMYCHAR:
				for (int i = 0; i<oppSide.size(); i++) {
					if (oppSide.get(i)!=null) list.add(i+7);
				}
				list.add(15);
				
			case ENEMYMINIONS:
				for (int i = 0; i<oppSide.size(); i++) {
					if (mySide.get(i)!=null) list.add(i+7);
				}
				
		default:
			break;
		}
		
		return list;
	}
	
	public MyTurnState damageRandomHittable(TargetsType targets, int amount) {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		int possibilities = getHittable(targets).size();
		
		switch (targets) {
			case  ALLYCHAR:
				for (int j : getHittable(TargetsType.ALLYMINIONS)) {
					list.add(new StateProbabilityPair((mySide.get(j)).damage(this,amount) , (double)1 / possibilities));
				}
				list.add(new StateProbabilityPair(hero.damage(this,amount), (double)1 /possibilities));
			
			case  ENEMYCHAR:
				for (int j : getHittable(TargetsType.ENEMYMINIONS)) {
					list.add(new StateProbabilityPair((oppSide.get(j-7)).damage(this,amount) , (double)1 / possibilities));
				}
				list.add(new StateProbabilityPair(enemy.damage(this,amount), (double)1 /possibilities));
		default:
			break;
		}
		
		return new RandomState(list);
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
		
		for (int position : positionsInPlayOrder) {
			if (position<7 && !exceptions.contains(mySide.get(position))) {
				if (!(defenders.equals(TargetsType.ENEMYCHAR) || defenders.equals(TargetsType.ENEMYMINIONS))) minions.add(mySide.get(position));
			}
			else if (!exceptions.contains(mySide.get(position))) {
				if (!(defenders.equals(TargetsType.ALLYCHAR) || defenders.equals(TargetsType.ALLYMINIONS))) minions.add(oppSide.get(position-7));
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
				else {int additional = amounts.get(j) - newChar.getArmour(); newChar.setArmour(0); newChar.setHP(hero.getHP()-additional);}
			
			if (newChar.getMyPos()==14) newHero = newChar;
			else newEnemy = newChar;
			}
		}
		
		for (int j = 0; j<minions.size(); j++) {
			if (!(minions.get(j)).isImmune()) {
				Minion newMinion = new Minion(minions.get(j));
				
				if (newMinion.isDivineShield()) newMinion.setDivineShield(false);
				else if (newMinion.getDamageTaken() + amounts.get(j+heroes.size()) >= newMinion.getMaxHP()) destroys.add(newMinion);
				else newMinion.setDamageTaken(newMinion.getDamageTaken()+amounts.get(j+heroes.size()));
				
				if (newMinion.getMyPos()<7) newMySide.set(newMinion.getMyPos(), newMinion);
				else newOppSide.set(newMinion.getMyPos()-7, newMinion);
			}
				
		}
		BoardState tempstate = new BoardState(viewType,newHero,newEnemy,newOppSide,newMySide,positionsInPlayOrder,enemyHandSize);
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
		
		ArrayList<Integer> newPositionsInPlayOrder = new ArrayList<Integer>();
		newPositionsInPlayOrder.addAll(positionsInPlayOrder);
		
		ArrayList<Integer> removedPositions = new ArrayList<Integer>();
		ArrayList<Integer> removed = new ArrayList<Integer>();
		
		for (Minion minion : minions) {
			removedPositions.add(minion.getMyPos());
			removed.add(minion.getMyPos());
	
			newPositionsInPlayOrder.removeAll(removed);
			
			if (minion.getMyPos()<7) {
				for (int position : newPositionsInPlayOrder) {
					if (position < 7 && position > minion.getMyPos()) newPositionsInPlayOrder.set(newPositionsInPlayOrder.indexOf(position), position-1);
				}
			}
			else {
				for (int position : newPositionsInPlayOrder) {
					if (position >= 7 && position > minion.getMyPos()) newPositionsInPlayOrder.set(newPositionsInPlayOrder.indexOf(position), position-1);
				}
			}
			removed.clear();
		}
		
		ArrayList<Integer> removedPositionsInOrder = (ArrayList<Integer>) positionsInPlayOrder.clone();
		removedPositionsInOrder.retainAll(removedPositions);
		
		newPositionsInPlayOrder.removeAll(removedPositions);
		
		ArrayList<Minion> deathOrder = new ArrayList<Minion>();
		for (int position: removedPositionsInOrder) {
			if (position<7) {
				deathOrder.add(newMySide.get(position));
				newMySide.remove(position);
			}
			else {
				deathOrder.add(newOppSide.get(position-7));
				newOppSide.remove(position-7);
			}
		}
		
		MyTurnState tempstate = new BoardState(viewType,hero,enemy,newOppSide,newMySide,newPositionsInPlayOrder,enemyHandSize);
		for (Minion minion : deathOrder) {
			for (Deathrattle deathrattle : minion.getDeathrattles()) {
				tempstate = deathrattle.trigger(minion, tempstate);
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
		
		for (int position : positionsInPlayOrder) {
			if (position<7 && !exceptions.contains(mySide.get(position))) {
				if (!(defenders.equals(TargetsType.ENEMYCHAR) || defenders.equals(TargetsType.ENEMYMINIONS))) minions.add(mySide.get(position));
			}
			else if (!exceptions.contains(mySide.get(position))) {
				if (!(defenders.equals(TargetsType.ALLYCHAR) || defenders.equals(TargetsType.ALLYMINIONS))) minions.add(oppSide.get(position-7));
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
			
			if (newChar.getMyPos()==14) newHero = newChar;
			else newEnemy = newChar;
			}
		}
		
		for (int j = 0; j<minions.size(); j++) {
			newMinion = new Minion(minions.get(j));
			
			if (newMinion.getDamageTaken() <= amounts.get(j)) newMinion.setDamageTaken(0);
			else newMinion.setDamageTaken(newMinion.getDamageTaken()-amounts.get(j));
			if (newMinion.getMyPos()<7) newMySide.set(newMinion.getMyPos(), newMinion);
			else newOppSide.set(newMinion.getMyPos()-7, newMinion);
		}
		return new BoardState(viewType,hero,enemy,newOppSide,newMySide,positionsInPlayOrder,enemyHandSize);
	}
	
	@SuppressWarnings("unchecked")
	public MyTurnState applyBuff(double minionID, Buff buff) {
		Minion minion;
		if (!(findMinion(minionID)==null)) {
			minion = findMinion(minionID); 
			
			if (minion.getMyPos()<7) {
				ArrayList<Minion> newMySide = (ArrayList<Minion>) mySide.clone();
				Minion newMinion = minion.applyBuff(buff);
			
				newMySide.set(minion.getMyPos(), newMinion);
				return new BoardState(viewType,hero,enemy,newMySide,oppSide,positionsInPlayOrder,enemyHandSize);
			}
			else {
				ArrayList<Minion> newOppSide = (ArrayList<Minion>) oppSide.clone();
				Minion newMinion = minion.applyBuff(buff);
				
				newOppSide.set(minion.getMyPos()-7, newMinion);
				return new BoardState(viewType,hero,enemy,mySide,newOppSide,positionsInPlayOrder,enemyHandSize);
			}
		}
		else return this;
	}
	
	@SuppressWarnings("unchecked")
	public MyTurnState applyTempBuff(double minionID, Buff buff) {
		Minion minion;
		if (!findMinion(minionID).equals(null)) {
			minion = findMinion(minionID); 
			
			if (minion.getMyPos()<7) {
				ArrayList<Minion> newMySide = (ArrayList<Minion>) mySide.clone();
				Minion newMinion = minion.applyTempBuff(buff);
			
				newMySide.set(minion.getMyPos(), newMinion);
				return new BoardState(viewType,hero,enemy,newMySide,oppSide,positionsInPlayOrder,enemyHandSize);
			}
			else {
				ArrayList<Minion> newOppSide = (ArrayList<Minion>) oppSide.clone();
				Minion newMinion = minion.applyTempBuff(buff);
				
				newOppSide.set(minion.getMyPos()-7, newMinion);
				return new BoardState(viewType,hero,enemy,mySide,newOppSide,positionsInPlayOrder,enemyHandSize);
			}
		}
		else return this;
	}
	
	@SuppressWarnings("unchecked")
	public MyTurnState removeBuff(double minionID, double id) {
		Minion minion;
		if (!findMinion(minionID).equals(null)) {
			minion = findMinion(minionID); 
	
			if (minion.getMyPos()<7) {
				ArrayList<Minion> newMySide = (ArrayList<Minion>) mySide.clone();
				Minion newMinion = minion.removeBuff(id);
			
				newMySide.set(minion.getMyPos(), newMinion);
				return new BoardState(viewType,hero,enemy,newMySide,oppSide,positionsInPlayOrder,enemyHandSize);
			}
			else {
				ArrayList<Minion> newOppSide = (ArrayList<Minion>) oppSide.clone();
				Minion newMinion = minion.removeBuff(id);
			
				newOppSide.set(minion.getMyPos()-7, newMinion);
				return new BoardState(viewType,hero,enemy,mySide,newOppSide,positionsInPlayOrder,enemyHandSize);
			}
		}
		else return this;
	}
	
	public MyTurnState silence(double minionID) {
		if (!findMinion(minionID).equals(null)) {
			Minion minion = findMinion(minionID); 
			return minion.silence(this);
		}
		else return this;
	}
	
	public MyTurnState applyAuras(Minion summonedMinion) {
		MyTurnState tempstate = this;
		for (int position : positionsInPlayOrder) {
				Minion minion;
				if (position<7) minion = mySide.get(position);
				else minion = oppSide.get(position-7);
				
				if (minion.getId()!=summonedMinion.getId()) {
					for (Aura aura : minion.getAuras()) {
						tempstate = aura.apply(tempstate, minion, summonedMinion);
					}
					for (Aura aura : summonedMinion.getAuras()) {
						tempstate = aura.apply(tempstate, summonedMinion, minion);
					}
				}
		}
		return tempstate;
	}
	
	public MyTurnState removeAuras(Minion removedMinion) {
		MyTurnState tempstate = this;
		for (int position : positionsInPlayOrder) {
				Minion minion;
				if (position<7) minion = mySide.get(position);
				else minion = oppSide.get(position-7);
				
				if (minion.getId()!=removedMinion.getId()) {
					for (Aura aura : minion.getAuras()) {
						tempstate = aura.remove(tempstate, minion, removedMinion);
					}
					for (Aura aura : removedMinion.getAuras()) {
						tempstate = aura.remove(tempstate, removedMinion, minion);
					}
				}
		}
		return tempstate;
	}
	
	public MyTurnState doSummonEffects(Minion summonedMinion) {
		MyTurnState tempstate = this;
		for (int position : positionsInPlayOrder) {
				Minion minion;
				if (position<7) minion = mySide.get(position);
				else minion = oppSide.get(position-7);
				
				if (minion.getId()!=summonedMinion.getId()) {
					for (SummonEffect summonEffect : minion.getSummonEffects()) {
						tempstate = summonEffect.perform(tempstate,minion,summonedMinion);
					}
				}
		}
		return tempstate;
	}
	
	public MyTurnState doDeathEffects(Minion destroyedMinion) {
		MyTurnState tempstate = this;
		for (int position : positionsInPlayOrder) {
				Minion minion;
				if (position<7) minion = mySide.get(position);
				else minion = oppSide.get(position-7);
				
				if (minion.getId()!=destroyedMinion.getId()) {
					for (DeathEffect deathEffect : minion.getDeathEffects()) {
						tempstate = deathEffect.perform(tempstate,minion,destroyedMinion);
					}
				}
		}
		return tempstate;
	}
	
	public MyTurnState doStartTurnEffects(Hero currentHero) {
		
		Hero newHero = hero.fresh();
		newHero.setCurrentMana(hero.getTotalMana()+1);
		newHero.setTotalMana(hero.getTotalMana()+1);
		newHero.setReady(true);
		
		MyTurnState tempstate;
		
		if (hero.getMyPos()==14) {
		
			ArrayList<Minion> newMySide = new ArrayList<Minion>();
		
			for (Minion minion : mySide) {
				Minion newMinion = new Minion(minion);
				if (newMinion.isSummonSickness()) newMinion.setSummonSickness(false);
				newMinion.setAttacksTaken(0);
				newMySide.add(minion.getMyPos(),newMinion);
			}
		
			tempstate = new BoardState(viewType,newHero,enemy,oppSide,newMySide,positionsInPlayOrder,enemyHandSize);
		}
		else {
			
			ArrayList<Minion> newOppSide = new ArrayList<Minion>();
			
			for (Minion minion : oppSide) {
				Minion newMinion = new Minion(minion);
				if (newMinion.isSummonSickness()) newMinion.setSummonSickness(false);
				newMinion.setAttacksTaken(0);
				newOppSide.set(minion.getMyPos()-7,newMinion);
			}
			
			tempstate = new BoardState(viewType,hero,newHero,newOppSide,mySide,positionsInPlayOrder,enemyHandSize);
		}
		
		for (int position : positionsInPlayOrder) {
			Minion minion;
			if (position<7) minion = mySide.get(position);
			else minion = oppSide.get(position-7);
			for (StartTurnEffect startTurnEffect : minion.getStartTurnEffects()) {
					tempstate = startTurnEffect.perform(tempstate,hero);
				}
			}
		
		if (newHero.getMyPos()==14) return tempstate.drawCard();
		else return tempstate.enemyDrawCard();
	}
	
	public MyTurnState doEndTurnEffects(Hero hero) {
		MyTurnState tempstate = this;
		for (int position : positionsInPlayOrder) {
			Minion minion;
			if (position<7) minion = new Minion(mySide.get(position));
			else minion = new Minion(oppSide.get(position-7));
			
			minion = minion.removeTempBuffs();
			
			for (EndTurnEffect endTurnEffect : minion.getEndTurnEffects()) {
					tempstate = endTurnEffect.perform(tempstate,hero);
			}
			
		}
		return tempstate;
	}
	
	public MyTurnState heroAttack(double weaponID, Hero defender) {
		if (hero.getWeapon().getId()==weaponID) {
			Hero newHero = hero.fresh();
			newHero.setReady(false);
			
			MyTurnState tempstate = new BoardState(viewType,newHero,enemy,oppSide,mySide,positionsInPlayOrder,enemyHandSize);
			tempstate = tempstate.damage(defender,hero.getWeapon().getAtk());
			
			return tempstate.changeHeroWeaponAtkDurability(weaponID,0,-1);
		}
		else return this;
	}
	
	public MyTurnState heroAttack(double weaponID, Minion defender) {
		if (hero.getWeapon().getId()==weaponID && !findMinion(defender.getId()).equals(null)) {
			Minion target = findMinion(defender.getId());
			Hero newHero = hero.fresh();
			newHero.setReady(false);
			
			MyTurnState tempstate = new BoardState(viewType,newHero,enemy,oppSide,mySide,positionsInPlayOrder,enemyHandSize);
			tempstate.damage(hero,target.getAtk());
			tempstate = tempstate.damage(target,hero.getWeapon().getAtk());
			
			return tempstate.changeHeroWeaponAtkDurability(weaponID,0,-1);
		}
		else return this;
	}
	
	public MyTurnState changeHeroWeaponAtkDurability(double id, int amountAtk, int amountDurability) {
		if (hero.getWeapon().getId()==id) {
			Hero newTarget = hero.fresh();
			Weapon weapon = (hero.getWeapon()).fresh();
			weapon.setAtk(weapon.getAtk() + amountAtk);
			weapon.setDurability(weapon.getDurability() + amountDurability);
			if (weapon.getAtk()<=0) return newTarget.destroyWeapon(this);
			else if (weapon.getDurability()<=0) return newTarget.destroyWeapon(this);
			else {
				newTarget.setWeapon(weapon);
				return new BoardState(viewType,newTarget,enemy,oppSide,mySide,positionsInPlayOrder,enemyHandSize);
			}
		}
		else return this;
}
	
	public MyTurnState changeEnemyWeaponAtkDurability(double id, int amountAtk, int amountDurability) {
			if (enemy.getWeapon().getId()==id) {
				Hero newTarget = enemy.fresh();
				Weapon weapon = (enemy.getWeapon()).fresh();
				weapon.setAtk(weapon.getAtk() + amountAtk);
				weapon.setDurability(weapon.getDurability() + amountDurability);
				if (weapon.getAtk()<=0) return newTarget.destroyWeapon(this);
				else if (weapon.getDurability()<=0) return newTarget.destroyWeapon(this);
				else {
					newTarget.setWeapon(weapon);
					return new BoardState(viewType,hero,newTarget,oppSide,mySide,positionsInPlayOrder,enemyHandSize);
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
		else return new BoardState(viewType,hero,enemy,oppSide,mySide,positionsInPlayOrder,enemyHandSize+1);
	}
	
	public MyTurnState addCardToHand(Hero heroDrawing, PlayableCard card) {
		if (heroDrawing.getMyHandSize()<10) {
			Hero newHero = heroDrawing.fresh();
			Hand newHand = new Hand(heroDrawing.getMyHand().raw());
			newHand.add(newHand.getSize(),card);
			newHero.setMyHand(newHand);
			if (newHero.getMyPos()==14) return new BoardState(viewType,newHero,enemy,oppSide,mySide,positionsInPlayOrder,enemyHandSize);
			else return new BoardState(viewType,hero,newHero,oppSide,mySide,positionsInPlayOrder,enemyHandSize);
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
	public MyTurnState placeMinion(Minion minion) {
		return minion.place(this);
	}
	
	public BoardState resolveRNG() {
		return this;
	}
	
	public double getValue(Node n) {
		if (enemy.getHP()<=0) {System.out.println("Found game winning sequence"); return 0;}
		
		if (turnEnded) {
			int k = 20;
			for (Minion minion : oppSide) {
				if (minion.isDivineShield()) k += 1.6*minion.getCurrentHP();
				else k += minion.getCurrentHP();
				k += 0.8*minion.getAtk();
			}
			k += 0.3*enemy.getHP();
			for (Minion minion : mySide) {
				if (minion.isDivineShield()) k -= minion.getCurrentHP();
				else k -= 0.6*minion.getCurrentHP();
				k -= 0.4*minion.getAtk();
			}
			k -= 0.1*hero.getHP();
			return k;
		}
		else return (this.getActionResult(new EndTurn())).getValue(n);
	}
	
	public double getBestValue(Node n) {
		 double best = getValue(n);
		 if (best==0 || turnEnded) {
			 n.bestNode = n;
			 n.best = best;
			 return best;
		 }
		 else {
			Node newNode = new Node(n, new EndTurn(), getActionResult(new EndTurn()));
			n.bestNode = newNode;
			n.best = newNode.getBestValue();
			
			for (Action action : getApplicableActions()) {
				Node newnode = new Node(n, action, getActionResult(action));
				double nodebest = newnode.getBestValue();
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
	public MyTurnState performDR(Deathrattle deathrattle, PlayableCard card) {
		return deathrattle.perform(card,this);
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
		System.out.println("          "+enemy.getHP());
		System.out.println("");
		for (Minion minion : oppSide) {
			System.out.print(minion.getAtk()+"/"+minion.getCurrentHP()+"  ");
		}
		System.out.println("");
		System.out.println("");
		for (Minion minion : mySide) {
			System.out.print(minion.getAtk()+"/"+minion.getCurrentHP()+"  ");
		}
		System.out.println("");
		System.out.println("");
		System.out.println("          "+hero.getHP());
		(hero.getMyHand()).print();
	}

	public boolean isTurnEnded() {
		return turnEnded;
	}

	public void setTurnEnded(boolean turnEnded) {
		this.turnEnded = turnEnded;
	}

	@Override
	public MyTurnState viewBiased() {
		return new BoardState(ViewType.BIASED, hero, enemy, oppSide, mySide, positionsInPlayOrder, enemyHandSize);
	}
	
	public Minion findMinion(double minionID) {
		for (Minion minion : mySide) {
			if (minion.getId()==minionID) return minion;
		}
		for (Minion minion : oppSide) {
			if (minion.getId()==minionID) return minion;
		}
		return null;
	}
	
}
