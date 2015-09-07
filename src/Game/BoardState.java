package Game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.LinkedHashSet;

import Game.Actions.Attack;
import Game.Actions.FaceAttack;
import Game.Actions.HeroAttack;
import Game.Actions.HeroFaceAttack;
import Game.Actions.PlayCard;
import Game.Battlecrys.MinionBattlecry;
import Game.Battlecrys.WeaponBattlecry;
import Game.Cards.Spells.TargettedSpell.TargettedSpell;
import Game.Deathrattles.MinionDeathrattle;
import Game.Deathrattles.WeaponDeathrattle;
import Game.Heroes.Hero;
import Game.Minions.Minion;
import Game.SummonEffects.SummonEffect;
import Game.Weapons.Weapon;
import Search.Action;
import Search.Node;
import Search.State;

public class BoardState implements State {
	
	private Hero hero;
	private Hero enemy;
		
	private Minion[] oppSide = new Minion[7];
	private Minion[] mySide = new Minion[7];
		
	private Deck myDeck;
	private Hand myHand;
	
	private ArrayList<SummonEffect> summonEffects;
	
	public ArrayList<SummonEffect> getSummonEffects() {
		return summonEffects;
	}
	
	private int enemyHandSize;

	public BoardState(Hero hero, Hero enemy, Minion[] oppSide, Minion[] mySide, Deck myDeck, Hand myHand,ArrayList<SummonEffect> summonEffects, int enemyHandSize) {
		this.hero = hero;
		this.enemy = enemy;
		this.oppSide = oppSide;
		this.mySide = mySide;
		this.myDeck = myDeck;
		this.myHand = myHand;
		this.summonEffects = summonEffects;
		this.enemyHandSize = enemyHandSize;
	}

	@Override
	public Set<Action> getApplicableActions() {
		Set<Action> actions = new LinkedHashSet<Action>();
		
		//Add possible attacks by minions to the set of actions.
		for (int i = 0; i<7; i++) {
			Minion myMinion = mySide[i];
			if (myMinion != null  && myMinion.canAttack()) {
				if (enemy.isAttackable()) actions.add(new FaceAttack(myMinion,enemy));
				for (int j = 0; j<7; j++) {
					Minion oppMinion = getOppSide()[j];
					if (oppMinion != null && oppMinion.isAttackable()) actions.add(new Attack(myMinion,oppMinion));
				}
			}
		}
		
		//Add possible attacks by hero to the set of actions.
		if (hero.canAttack()) {
			if (enemy.isAttackable()) actions.add(new HeroFaceAttack(hero,enemy));
			for (int i = 0; i<7; i++) {
				Minion oppMinion = getOppSide()[i];
				if (oppMinion != null) {
					if (oppMinion.isAttackable()) actions.add(new HeroAttack(getHero(),oppMinion));
				}
			}
		}
		
		//Add hero power to set of actions.
		if (hero.getHeroPower().useable(this) && !hero.getPowerUsed()) actions.add(hero.getHeroPower());
		
		//Add playing cards in hand to set of actions.
		// Case matching on class types IMPLEMENT
		for (int i = 0; i < myHand.getSize(); i++) {
			if ((myHand.raw()).get(i) != null) {
				Card card = (myHand.raw()).get(i);
				if (card.getCost()<= hero.getCurrentMana()) { 
					if (card.getType().equals(CardType.MINION)){
						if (numberOfMinions()<7) {
							actions.add(new PlayCard(card,numberOfMinions(),i));
						}
					}
					else if (card.getType().equals(CardType.UNTARGETTEDSPELL)) {
						actions.add(new PlayCard(card,0,i));
					}
					else if (card.getType().equals(CardType.TARGETTEDSPELL)) {
						TargettedSpell tcard = (TargettedSpell) card;
						if (tcard.getTargets().equals(TargetsType.ALL)) {
							for (int j = 0; j<7 ; j++) {
								if (oppSide[j]!=null) actions.add(new PlayCard(card,j+7,i));
								if (mySide[j]!=null) actions.add(new PlayCard(card,j,i));
							}
							actions.add(new PlayCard(card,14,i));
							actions.add(new PlayCard(card,15,i));
						}
						else if (tcard.getTargets().equals(TargetsType.ALLMINIONS)) {
							for (int j = 0; j<7 ; j++) {
								if (oppSide[j]!=null) actions.add(new PlayCard(card,j+7,i));
								if (mySide[j]!=null) actions.add(new PlayCard(card,j,i));
							}
						}
					}
					else if (card.getType().equals(CardType.WEAPON)){
						actions.add(new PlayCard(card,0,i));
					}
				}
			}
		}
		return actions;
	}

	@Override
	public State getActionResult(Action action) {
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
            		if (oppSide[i] == null && other.oppSide[i] != null) return false;
            		else if (oppSide[i] != null && other.oppSide[i] == null) return false;
            		
            		if (oppSide[i] == null && other.oppSide[i] == null);
            		else if (!oppSide[i].equals(other.oppSide[i])) return false;
            	}
            	for (int i = 0; i<7; i++) {
            		if (mySide[i] == null && other.mySide[i] != null) return false;
            		else if (mySide[i] != null && other.mySide[i] == null) return false;
            		
            		if (mySide[i] == null && other.mySide[i] == null);
            		else if (!mySide[i].equals(other.mySide[i])) return false;
            	}
            	if (!myHand.equals(other.myHand)) return false;
        
            	 return true;
            }
		}
}
		
	
	@Override
	public int hashCode() {
		int k = 0;
		for (int i = 0; i<7; i++) {
    		if (mySide[i] != null) k += mySide[i].getAtk() * mySide[i].getHP();
		}
		
		for (int i = 0; i<7; i++) {
    		if (getOppSide()[i] != null) k += getOppSide()[i].getAtk() * getOppSide()[i].getHP();
		}
		return k;
	}

	public Minion[] getOppSide() {
		return oppSide;
	}

	public void setOppSide(Minion[] oppSide) {
		this.oppSide = oppSide;
	}

	public Minion[] getMySide() {
		return mySide;
	}

	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}

	public Hand getMyHand() {
		return myHand;
	}

	public void setMyHand(Hand myHand) {
		this.myHand = myHand;
	}

	public Deck getMyDeck() {
		return myDeck;
	}

	public void setMyDeck(Deck myDeck) {
		this.myDeck = myDeck;
	}

	public Hero getEnemy() {
		return enemy;
	}

	public void setEnemy(Hero enemy) {
		this.enemy = enemy;
	}
	
	public int getEnemyHandSize() {
		return enemyHandSize;
	}

	public void setEnemyHandSize(int enemyHandSize) {
		this.enemyHandSize = enemyHandSize;
	}

	public List<Integer> getHittable(TargetsType targets) {
		List<Integer> list = new LinkedList<Integer>();
		
		switch (targets) {
			case ALLYCHAR:
				for (int i = 0; i<7; i++) {
					if (mySide[i]!=null) list.add(i);
				}
				list.add(14);
			
			case ENEMYCHAR:
				for (int i = 7; i<14; i++) {
					if (oppSide[i-7]!=null) list.add(i);
				}
				list.add(15);
		default:
			break;
		}
		
		return list;
	}
	
	public RandomState damageRandomHittable(TargetsType targets, int amount) {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		int possibilities = getHittable(targets).size();
		
		switch (targets) {
			case  ALLYCHAR:
				for (int j : getHittable(TargetsType.ALLYMINIONS)) {
					list.add(new StateProbabilityPair(mySide[j].damage(this,amount) , (double)1 / (possibilities+1)));
				}
				list.add(new StateProbabilityPair(hero.damage(this,amount), (double)1 /(possibilities+1)));
			
			case  ENEMYCHAR:
				for (int j : getHittable(TargetsType.ENEMYMINIONS)) {
					list.add(new StateProbabilityPair(oppSide[j-7].damage(this,amount) , (double)1 / (possibilities+1)));
				}
				list.add(new StateProbabilityPair(enemy.damage(this,amount), (double)1 /(possibilities+1)));
		default:
			break;
		}
		
		return new RandomState(list);
	}
	
	public State damageTarget(int target, int amount) {
		if (target <14) {
			Minion defender;
			if (target<7) defender = mySide[target];
			else defender = oppSide[target-7];
			return defender.damage(this,amount);
		}
		
		else {
			Hero defender;
			if (target==14) defender = enemy;
			else defender = hero;
			return defender.damage(this,amount);
		}
	}
	
	public State damage(Minion minion, int amount) {
		return minion.damage(this, amount);
	}
	
	public State simultaneousDamage(ArrayList<Minion> minions,ArrayList<Integer> amounts) {
		ArrayList<Minion> destroys = new ArrayList<Minion>();
		Minion newMinion;
		Minion[] newMySide = new Minion[7];
		Minion[] newOppSide = new Minion[7];
		for (int i = 0; i<7; i++) {
			if (mySide[i] != null) newMySide[i] = mySide[i];
			if (oppSide[i] != null) newOppSide[i] = oppSide[i];
		}
		for (int j = 0; j<minions.size(); j++) {
			newMinion = new Minion(minions.get(j));
			if (newMinion.isDivineShield()) newMinion.setDivineShield(false);
			else if (amounts.get(j) >= newMinion.getHP()) destroys.add(newMinion);
			else newMinion.setHP(newMinion.getHP()-amounts.get(j));
			if (newMinion.getMyPos()<7) newMySide[newMinion.getMyPos()] = newMinion;
			else newOppSide[newMinion.getMyPos()-7] = newMinion;
		}
		BoardState tempstate = new BoardState(hero,enemy,newOppSide,newMySide,myDeck,myHand,summonEffects,enemyHandSize);
		return simultaneousDestroy(tempstate,destroys);
	}
	
	public State simultaneousDestroy(BoardState oldstate, ArrayList<Minion> minions) {
		Minion[] newMySide = new Minion[7];
		Minion[] newOppSide = new Minion[7];
		for (int i = 0; i<7; i++) {
			if (oldstate.getMySide()[i] != null) newMySide[i] = oldstate.getMySide()[i];
			if (oldstate.getOppSide()[i] != null) newOppSide[i] = oldstate.getOppSide()[i];
		}
		for (Minion minion : minions) {
			if (minion.getMyPos() <7) newMySide[minion.getMyPos()] = null;
			else newOppSide[minion.getMyPos()-7] = null;
		}
		tidyMinions(newMySide);
		tidyMinions(newOppSide);
		State tempstate = new BoardState(hero,enemy,newOppSide,newMySide,myDeck,myHand,summonEffects,enemyHandSize);
		for (Minion minion : minions) {
			for (MinionDeathrattle deathrattle : minion.getDeathrattles()) {
				tempstate = deathrattle.trigger(minion, tempstate);
			}
		}
		return tempstate;
		
	}
	
	public State changeWeaponDurability(Hero target, int amount) {
			Hero newTarget = target.fresh();
			Weapon weapon = (target.getWeapon()).fresh();
			weapon.setDurability(weapon.getDurability() + amount);
			if (weapon.getDurability()<=0) return newTarget.destroyWeapon(this);
			else {
				newTarget.setWeapon(weapon);
				if (newTarget.getMyPos()==14) return new BoardState(newTarget,enemy,oppSide,mySide,myDeck,myHand,summonEffects,enemyHandSize);
				else return new BoardState(hero,newTarget,oppSide,mySide,myDeck,myHand,summonEffects,enemyHandSize);
			}
	}
	
	@Override
	public State drawCard() {
		return myDeck.drawCard(this);
	}
	
	public BoardState enemyDrawCard() {
		return new BoardState(hero,enemy,oppSide,mySide,myDeck,myHand,summonEffects,enemyHandSize+1);
	}
	
	public int numberOfMinions() {
		int i = 0;
		while (mySide[i]!=null) i++;
		return i;
	}
	
	@Override
	public State placeMinion(Minion minion) {
		return minion.place(this);
	}
	
	public double getValue(Node n) {
		int k = 0;
		for (int i = 0; i<7; i++) {
			if (oppSide[i] != null) {
				if (oppSide[i].isDivineShield()) k += 1.6*oppSide[i].getHP();
				else k += oppSide[i].getHP();
				k += 0.8*oppSide[i].getAtk();
			}
		}
		k += 0.3*enemy.getHP();
		for (int i = 0; i<7; i++) {
			if (mySide[i] != null) {
				if (mySide[i].isDivineShield()) k -= mySide[i].getHP();
				else k -= 0.6*mySide[i].getHP();
				k -= 0.4*mySide[i].getAtk();
			}
		}
		k -= 0.1*hero.getHP();
		return k;
	}
	
	public double getBestValue(Node n) {
		 double best = getValue(n);
		 for (Action action : getApplicableActions()) {
			 Node newnode = new Node(n, action, getActionResult(action));
			 double nodebest = newnode.getBestValue();
			 if (nodebest < best) {
				 best = nodebest; 
				 n.bestNode = newnode;
				 n.best = best;
			 }
		 }
		    
		 return best;
	}
	
	@Override
	public State performBC(MinionBattlecry battlecry, Minion minion) {
		return battlecry.perform(minion,this);
	}

	@Override
	public State performDR(MinionDeathrattle deathrattle, Minion minion) {
		return deathrattle.perform(minion,this);
	}

	@Override
	public State performBC(WeaponBattlecry battlecry) {
		return battlecry.perform(this);
	}
	
	@Override
	public State performDR(WeaponDeathrattle deathrattle) {
		return deathrattle.perform(this);
	}
	
	@Override
	public State equipHeroWeapon(Weapon weapon) {
		return hero.equipWeapon(this, weapon);
	}
	
	@Override
	public State equipEnemyWeapon(Weapon weapon) {
		return enemy.equipWeapon(this, weapon);
	}
	
	//Helper function to keep minions properly arranged on their sides (removes gaps)
	private void tidyMinions(Minion[] minions) {
		int firstNull = -1;
		for (int i = 0; i<7; i++) {
			if (minions[i]==null) {
				if (firstNull==-1) firstNull = i;
			}
			else if (firstNull!=-1) {
				Minion tempMinion = new Minion(minions[i]);
				tempMinion.setMyPos(firstNull);
				minions[firstNull] = tempMinion;
				firstNull = -1;
				i = 0;
			}
		}
	}
	
}
