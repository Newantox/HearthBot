package Game;

import java.util.Set;
import java.util.LinkedHashSet;

import Game.Actions.Attack;
import Game.Actions.FaceAttack;
import Game.Actions.HeroAttack;
import Game.Actions.HeroFaceAttack;
import Game.Actions.PlayCard;
import Game.Heroes.Hero;
import Game.Minions.Minion;
import Search.Action;
import Search.State;

public class BoardState implements State {
	// IMPLEMENT: flattenBoard to remove gaps in board, to make it easier to deal with AoE clearing minions etc.
	private Hero hero;
	private Hero enemy;
		
	private int currentMana;
	private int totalMana;
		
	private Minion[] oppSide = new Minion[7];
	private Minion[] mySide = new Minion[7];
		
	private Set<Card> myDeck = new LinkedHashSet<Card>();
	private Card[] myHand = new Card[10];
	
	public BoardState(Hero hero, Hero enemy, int currentMana, int totalMana, Minion[] oppSide, Minion[] mySide, Set<Card> myDeck, Card[] myHand) {
		this.setHero(hero);
		this.setEnemy(enemy);
		this.setCurrentMana(currentMana);
		this.setTotalMana(totalMana);
		this.oppSide = oppSide;
		this.mySide = mySide;
		this.setMyDeck(myDeck);
		this.setMyHand(myHand);
	}

	@Override
	public Set<? extends Action> getApplicableActions() {
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
		for (int i = 0; i<10; i++) {
			if (myHand[i] != null) {
				if (myHand[i].getCost()<= currentMana) { 
					for (int j = 0; j<7 ; j++) {
						if (oppSide[j]!=null) actions.add(new PlayCard(myHand[i],j+7,i));
						if (mySide[j]!=null) actions.add(new PlayCard(myHand[i],j,i));
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
			if (getCurrentMana() != other.getCurrentMana()) return false;
			else if (getTotalMana() != other.getTotalMana())  return false;
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
            	for (int i = 0; i<10; i++) {
            		if (myHand[i] == null && other.myHand[i] != null) return false;
            		else if (myHand[i] != null && other.myHand[i] == null) return false;
            		
            		if (myHand[i] == null && other.myHand[i] == null);
            		else if (myHand[i] != other.myHand[i]) return false;
            	}
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

	public Card[] getMyHand() {
		return myHand;
	}

	public void setMyHand(Card[] myHand) {
		this.myHand = myHand;
	}

	public int getTotalMana() {
		return totalMana;
	}

	public void setTotalMana(int totalMana) {
		this.totalMana = totalMana;
	}

	public Set<Card> getMyDeck() {
		return myDeck;
	}

	public void setMyDeck(Set<Card> myDeck) {
		this.myDeck = myDeck;
	}

	public int getCurrentMana() {
		return currentMana;
	}

	public void setCurrentMana(int currentMana) {
		this.currentMana = currentMana;
	}

	public Hero getEnemy() {
		return enemy;
	}

	public void setEnemy(Hero enemy) {
		this.enemy = enemy;
	}
}
