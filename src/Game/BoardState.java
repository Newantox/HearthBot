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
import Game.Cards.Spells.TargettedSpell.TargettedSpell;
import Game.Heroes.Hero;
import Game.Minions.Minion;
import Game.SummonEffects.SummonEffect;
import Search.Action;
import Search.State;

public class BoardState implements State {
	
	public StateType getStatetype() {
		return StateType.BOARD;
	}
	
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

	public BoardState(Hero hero, Hero enemy, Minion[] oppSide, Minion[] mySide, Deck myDeck, Hand myHand) {
		this.hero = hero;
		this.enemy = enemy;
		this.oppSide = oppSide;
		this.mySide = mySide;
		this.myDeck = myDeck;
		this.myHand = myHand;
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
					if (oppMinion != null && oppMinion.isAttackable()) {System.out.println(myMinion.getName()+" "+oppMinion.getName()); actions.add(new Attack(myMinion,oppMinion));}
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
						if (mySide[6]==null) {
							actions.add(new PlayCard(card,6,i));
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
	
	public List<Integer> getHittableEnemies() {
		List<Integer> list = new LinkedList<Integer>();
		for (int i = 7; i<14; i++) {
			if (oppSide[i-7]!=null) list.add(i);
		}
		return list;
	}
	
	public RandomState damageRandomHittableEnemy(int amount, double probmodifier) {
		List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
		int possibilities = getHittableEnemies().size();
		for (int j : getHittableEnemies()) {
			list.add(new StateProbabilityPair(oppSide[j-7].damage(this,amount) , probmodifier / (possibilities+1)));
		}
		list.add(new StateProbabilityPair(enemy.damage(this,amount), probmodifier /(possibilities+1)));
		System.out.println("Enemy"+enemy.getHP());
		return new RandomState(list);
	}
	
	public BoardState damageTarget(int target, int amount) {
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
	
	public RandomState drawCard() {
		return myDeck.drawCard(this);
	}
	
	public BoardState enemyDrawCard() {
		return this;
	}
	
}
