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
import Game.Battlecrys.MinionBattlecry;
import Game.Battlecrys.WeaponBattlecry;
import Game.Cards.Spells.TargettedSpell.TargettedSpell;
import Game.DeathEffects.DeathEffect;
import Game.Deathrattles.MinionDeathrattle;
import Game.Deathrattles.WeaponDeathrattle;
import Game.EndTurnEffects.EndTurnEffect;
import Game.Heroes.Hero;
import Game.Inspires.MinionInspire;
import Game.Inspires.WeaponInspire;
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
	private ArrayList<Integer> positionsInPlayOrder;
	
	//Biased ViewType tracker, as substitute for updating enemy hand.
	private int enemyHandSize;

	public BoardState(ViewType viewType, Hero hero, Hero enemy, ArrayList<Minion> oppSide, ArrayList<Minion> mySide, ArrayList<Integer> positionsInPlayOrder, int enemyHandSize) {
		this.viewType = viewType;
		this.hero = hero;
		this.enemy = enemy;
		this.oppSide = oppSide;
		this.mySide = mySide;
		this.positionsInPlayOrder = positionsInPlayOrder;
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
					if (oppMinion.isAttackable(this)) actions.add(new Attack(myMinion,oppMinion));
				}
			}
		}
		
		//Add possible attacks by hero to the set of actions.
		if (hero.canAttack()) {
			if (enemy.isAttackable()) actions.add(new HeroFaceAttack(hero,enemy));
			for (Minion oppMinion : oppSide) {
				if (oppMinion.isAttackable(this)) actions.add(new HeroAttack(getHero(),oppMinion));
			}
		}
		
		//Add hero power to set of actions.
		if (hero.getHeroPower().useable(this) && !hero.getPowerUsed()) actions.add(hero.getHeroPower());
		
		//Add playing cards in hand to set of actions.
		// Case matching on class types IMPLEMENT
		for (int i = 0; i < (hero.getMyHandSize()); i++) {
			if (((hero.getMyHand()).raw()).get(i) != null) {
				Card card = ((hero.getMyHand()).raw()).get(i);
				if (card.getCost()<= hero.getCurrentMana()) { 
					if (card.getType().equals(CardType.MINION)){
						if (numberOfAlliedMinions()<7) {
							actions.add(new PlayCard(card,numberOfAlliedMinions(),i));
						}
					}
					else if (card.getType().equals(CardType.UNTARGETTEDSPELL)) {
						actions.add(new PlayCard(card,-1,i));
					}
					else if (card.getType().equals(CardType.TARGETTEDSPELL)) {
						TargettedSpell tcard = (TargettedSpell) card;
						if (tcard.getTargets().equals(TargetsType.ALL)) {
							for (int position : positionsInPlayOrder) {
								actions.add(new PlayCard(card,position,i));
							}
							actions.add(new PlayCard(card,14,i));
							actions.add(new PlayCard(card,15,i));
						}
						else if (tcard.getTargets().equals(TargetsType.ALLMINIONS)) {
							for (int position : positionsInPlayOrder) {
								actions.add(new PlayCard(card,position,i));
							}
						}
					}
					else if (card.getType().equals(CardType.WEAPON)){
						actions.add(new PlayCard(card,-1,i));
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
    		if (mySide.get(i) != null) k += (mySide.get(i)).getAtk() * (mySide.get(i)).getHP();
		}
		
		for (int i = 0; i<7; i++) {
    		if (oppSide.get(i) != null) k += (oppSide.get(i)).getAtk() * (oppSide.get(i)).getHP();
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
	
	public RandomState damageRandomHittable(TargetsType targets, int amount) {
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
	
	public MyTurnState damageTarget(int target, int amount) {
		if (target <14) {
			Minion defender;
			if (target<7) defender = mySide.get(target);
			else defender = oppSide.get(target);
			return defender.damage(this,amount);
		}
		
		else {
			Hero defender;
			if (target==14) defender = enemy;
			else defender = hero;
			return defender.damage(this,amount);
		}
	}
	
	public MyTurnState spellDamageTarget(int target, int damage) {
		if (target==14 || target<7) return damageTarget(target,damage+getTotalEnemySpellDamage());
		else return damageTarget(target,damage+getTotalAlliedSpellDamage());
	}
	
	public MyTurnState damage(Minion minion, int amount) {
		return minion.damage(this, amount);
	}
	
	@SuppressWarnings("unchecked")
	public MyTurnState simultaneousDamage(ArrayList<Minion> minions,ArrayList<Integer> amounts) {
		ArrayList<Minion> destroys = new ArrayList<Minion>();
		Minion newMinion;
		
		ArrayList<Minion> newMySide = (ArrayList<Minion>) mySide.clone();
		ArrayList<Minion> newOppSide = (ArrayList<Minion>) oppSide.clone();
		
		for (int j = 0; j<minions.size(); j++) {
			newMinion = new Minion(minions.get(j));
			
			if (newMinion.isDivineShield()) newMinion.setDivineShield(false);
			else if (amounts.get(j) >= newMinion.getHP()) destroys.add(newMinion);
			else newMinion.setHP(newMinion.getHP()-amounts.get(j));
			if (newMinion.getMyPos()<7) newMySide.set(newMinion.getMyPos(), newMinion);
			else newOppSide.set(newMinion.getMyPos()-7, newMinion);
		}
		BoardState tempstate = new BoardState(viewType,hero,enemy,newOppSide,newMySide,positionsInPlayOrder,enemyHandSize);
		return tempstate.simultaneousDestroy(destroys);
	}
	
	public MyTurnState simultaneousSpellDamage(ArrayList<Minion> minions,ArrayList<Integer> amounts) {
		ArrayList<Integer> newAmounts = new ArrayList<Integer>();
		int k = getTotalAlliedSpellDamage();
		for (int n : amounts) newAmounts.add(n+k);
		return simultaneousDamage(minions,newAmounts);
	}
	
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
			for (MinionDeathrattle deathrattle : minion.getDeathrattles()) {
				tempstate = deathrattle.trigger(minion, tempstate);
			}
		}
		return tempstate;
		
	}
	
	public MyTurnState applyAuras(Minion summonedMinion) {
		MyTurnState tempstate = this;
		for (int position : positionsInPlayOrder) {
			if ((summonedMinion.getMyPos())!=position) {
				Minion minion;
				if (position<7) minion = mySide.get(position);
				else minion = oppSide.get(position-7);
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
			if ((removedMinion.getMyPos())!=position) {
				Minion minion;
				if (position<7) minion = mySide.get(position);
				else minion = oppSide.get(position-7);
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
			if ((summonedMinion.getMyPos())!=position) {
		
				Minion minion;
				if (position<7) minion = mySide.get(position);
				else minion = oppSide.get(position-7);
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
			if ((destroyedMinion.getMyPos())!=position) {
				Minion minion;
				if (position<7) minion = mySide.get(position);
				else minion = oppSide.get(position-7);
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
				newMinion.setReady(true);
				newMySide.set(minion.getMyPos(),newMinion);
			}
		
			tempstate = new BoardState(viewType,newHero,enemy,oppSide,newMySide,positionsInPlayOrder,enemyHandSize);
		}
		else {
			
			ArrayList<Minion> newOppSide = new ArrayList<Minion>();
			
			for (Minion minion : oppSide) {
				Minion newMinion = new Minion(minion);
				newMinion.setReady(true);
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
			if (position<7) minion = mySide.get(position);
			else minion = oppSide.get(position-7);
			for (EndTurnEffect endTurnEffect : minion.getEndTurnEffects()) {
					tempstate = endTurnEffect.perform(tempstate,hero);
			}
		}
		return tempstate;
	}
	
	public MyTurnState changeHeroWeaponAtkDurability(int amountAtk, int amountDurability) {
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
	
	public MyTurnState changeEnemyWeaponAtkDurability(int amountAtk, int amountDurability) {
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
				if (minion.isDivineShield()) k += 1.6*minion.getHP();
				else k += minion.getHP();
				k += 0.8*minion.getAtk();
			}
			k += 0.3*enemy.getHP();
			for (Minion minion : mySide) {
				if (minion.isDivineShield()) k -= minion.getHP();
				else k -= 0.6*minion.getHP();
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
	public MyTurnState changeAtkHP(Minion minion, int amountAtk , int amountHP) {
		return minion.changeAtkHP(this, amountAtk, amountHP);
	}
	
	@Override
	public MyTurnState changeAttributes(Minion minion, boolean charge, boolean divineshield, boolean taunt, boolean stealth, boolean windfury, int spelldamage, boolean frozen) {
		return minion.changeAttributes(this,charge,divineshield,taunt,stealth,windfury,spelldamage,frozen);
	}
	
	@Override
	public MyTurnState performBC(MinionBattlecry battlecry, Minion minion) {
		return battlecry.perform(minion,this);
	}

	@Override
	public MyTurnState performDR(MinionDeathrattle deathrattle, Minion minion) {
		return deathrattle.perform(minion,this);
	}
	
	@Override
	public MyTurnState performInspire(MinionInspire inspire, Minion minion) {
		return inspire.perform(minion,this);
	}

	@Override
	public MyTurnState performBC(WeaponBattlecry battlecry) {
		return battlecry.perform(this);
	}
	
	@Override
	public MyTurnState performDR(WeaponDeathrattle deathrattle) {
		return deathrattle.perform(this);
	}

	@Override
	public MyTurnState performInspire(WeaponInspire inspire) {
		return inspire.perform(this);
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
			System.out.print(minion.getAtk()+"/"+minion.getHP()+"  ");
		}
		System.out.println("");
		System.out.println("");
		for (Minion minion : mySide) {
			System.out.print(minion.getAtk()+"/"+minion.getHP()+"  ");
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
	
}
