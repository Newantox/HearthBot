package Game.Minions;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

import Game.Character;
import Game.BoardState;
import Game.CardType;
import Game.MyTurnState;
import Game.Auras.Aura;
import Game.Battlecrys.Battlecry;
import Game.Buffs.Buff;
import Game.DeathEffects.DeathEffect;
import Game.Deathrattles.Deathrattle;
import Game.EndTurnEffects.EndTurnEffect;
import Game.Heroes.Hero;
import Game.Inspires.Inspire;
import Game.StartTurnEffects.StartTurnEffect;
import Game.SummonEffects.SummonEffect;

public class Minion implements Character {
	private double id;
	private String name;
	private int mypos;
	private int cost;
	private int atk;
	private int damageTaken;
	private int maxHP;
	private int attacksTaken;
	private int maxAttacks;
	private Race race;
	private boolean summonSickness;
	private boolean charge;
	private boolean divineshield;
	private boolean taunt;
	private boolean stealth;
	private int spelldamage;
	private boolean frozen;
	private boolean immune;
	
	private ArrayList<Buff> buffs;
	private ArrayList<Buff> tempbuffs;
	
	protected ArrayList<Battlecry> battlecrys;
	protected ArrayList<Deathrattle> deathrattles;
	protected ArrayList<Inspire> inspires;
	
	protected ArrayList<Aura> auras;
	protected ArrayList<SummonEffect> summonEffects;
	protected ArrayList<DeathEffect> deathEffects;
	protected ArrayList<StartTurnEffect> startTurnEffects;
	protected ArrayList<EndTurnEffect> endTurnEffects;
	
	public Minion (String name, int mypos, int cost, int atk,int maxHP) {
		this.id = Math.random();
		this.name = name;
		this.mypos = mypos;
		this.cost = cost;
		this.atk = atk;
		this.damageTaken = 0;
		this.maxHP = maxHP;
		this.setRace(Race.NORMAL);
		this.summonSickness = true;
		this.charge = false;
		this.divineshield = false;
		this.taunt = false;
		this.stealth = false;
		this.attacksTaken = 0;
		this.maxAttacks = 1;
		this.setSpelldamage(0);
		this.frozen = false;
		this.immune = false;
		
		this.buffs = new ArrayList<Buff>();
		this.tempbuffs = new ArrayList<Buff>();
		
		this.battlecrys = new ArrayList<Battlecry>();
		this.deathrattles = new ArrayList<Deathrattle>();
		this.inspires = new ArrayList<Inspire>();
		
		this.auras = new ArrayList<Aura>();
		this.summonEffects = new ArrayList<SummonEffect>();
		this.deathEffects = new ArrayList<DeathEffect>();
		this.startTurnEffects = new ArrayList<StartTurnEffect>();
		this.endTurnEffects = new ArrayList<EndTurnEffect>();
	}
	
	public ArrayList<Buff> getBuffs() {
		return buffs;
	}

	public void setBuffs(ArrayList<Buff> buffs) {
		this.buffs = buffs;
	}
	
	public ArrayList<Buff> getTempBuffs() {
		return tempbuffs;
	}

	public void setTempBuffs(ArrayList<Buff> tempbuffs) {
		this.tempbuffs = tempbuffs;
	}

	public ArrayList<StartTurnEffect> getStartTurnEffects() {
		return startTurnEffects;
	}

	public void setStartTurnEffects(ArrayList<StartTurnEffect> startTurnEffects) {
		this.startTurnEffects = startTurnEffects;
	}

	public ArrayList<EndTurnEffect> getEndTurnEffects() {
		return endTurnEffects;
	}

	public void setEndTurnEffects(ArrayList<EndTurnEffect> endTurnEffects) {
		this.endTurnEffects = endTurnEffects;
	}

	public Minion(Minion m) {
		this.setId(m.getId());
		this.setName(m.getName());
		this.setMyPos(m.getMyPos());
		this.setCost(m.getCost());
		this.setAtk(m.getAtk());
		this.setDamageTaken(m.getDamageTaken());
		this.setMaxHP(m.getMaxHP());
		this.setRace(m.getRace());
		this.setSummonSickness(m.isSummonSickness());
		this.setCharge(m.isCharge());
		this.setDivineShield(m.isDivineShield());
		this.setTaunt(m.isTaunt());
		this.setStealth(m.isStealth());
		this.setAttacksTaken(m.getAttacksTaken());
		this.setMaxAttacks(m.getMaxAttacks());
		this.setSpelldamage(m.getSpelldamage());
		this.setFrozen(m.isFrozen());
		this.setImmune(m.isImmune());
		
		this.buffs = m.getBuffs();
		this.tempbuffs = m.getTempBuffs();
		
		this.battlecrys = m.getBattlecrys();
		this.deathrattles = m.getDeathrattles();
		this.inspires = m.getInspires();
		
		this.auras = m.getAuras();
		this.summonEffects = m.getSummonEffects();
		this.deathEffects = m.getDeathEffects();
		this.startTurnEffects = m.getStartTurnEffects();
		this.endTurnEffects = m.getEndTurnEffects();
		
	}
	
	public Minion(int target) {
		try {
			Constructor<? extends Minion> constructor = getClass().getDeclaredConstructor(int.class) ;
		    constructor.newInstance(target) ;
		}
		catch( Exception e ) {
		    System.out.println( e ) ;
		}
	}
	
	public Minion() {
		this(0);
	}
	
	public ArrayList<Battlecry> getBattlecrys() {
		return battlecrys;
	}

	public void setBattlecrys(ArrayList<Battlecry> battlecrys) {
		this.battlecrys = battlecrys;
	}

	public ArrayList<Deathrattle> getDeathrattles() {
		return deathrattles;
	}

	public void setDeathrattles(ArrayList<Deathrattle> deathrattles) {
		this.deathrattles = deathrattles;
	}

	public ArrayList<Inspire> getInspires() {
		return inspires;
	}

	public void setInspires(ArrayList<Inspire> inspires) {
		this.inspires = inspires;
	}

	
	public int getMyPos() {
		return mypos;
	}

	public void setMyPos(int mypos) {
		this.mypos = mypos;
	}
	
	public boolean isStealth() {
		return stealth;
	}

	public void setStealth(boolean stealth) {
		this.stealth = stealth;
	}

	public boolean isCharge() {
		return charge;
	}

	public void setCharge(boolean charge) {
		this.charge = charge;
	}

	public boolean isDivineShield() {
		return divineshield;
	}

	public void setDivineShield(boolean divineshield) {
		this.divineshield = divineshield;
	}

	public boolean isTaunt() {
		return taunt;
	}

	public void setTaunt(boolean taunt) {
		this.taunt = taunt;
	}

	public boolean canAttack() {
		return ((charge || !summonSickness) && (attacksTaken<maxAttacks) && (getAtk() > 0));
	}
	
	public boolean isAttackable(BoardState oldstate) {
		if (mypos<7) return (taunt || !oldstate.isTauntOnSide(oldstate.getMySide()));
		else return (taunt || !oldstate.isTauntOnSide(oldstate.getOppSide()));
	}
	
	public boolean isTargettable() {
		return !stealth;
	}

	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	public int getDamageTaken() {
		return damageTaken;
	}
	
	public int getCurrentHP() {
		return (getMaxHP()-getDamageTaken());
	}

	public void setMaxHP(int max) {
		this.maxHP = max;
	}
	
	public int getMaxHP() {
		return maxHP;
	}

	public void setDamageTaken(int damageTaken) {
		this.damageTaken = damageTaken;
	}

	public boolean isSummonSickness() {
		return summonSickness;
	}

	public void setSummonSickness(boolean summonSickness) {
		this.summonSickness = summonSickness;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public boolean isFrozen() {
		return frozen;
	}

	public void setFrozen(boolean frozen) {
		this.frozen = frozen;
	}
	
	public void addDeathrattle(Deathrattle DR) {
		deathrattles.add(DR);
	}
	
	public void addAura(Aura aura) {
		auras.add(aura);
	}
	
	public ArrayList<Aura> getAuras() {
		return auras;
	}
	
	public void addSummonEffect(SummonEffect SE) {
		summonEffects.add(SE);
	}
	
	public ArrayList<SummonEffect> getSummonEffects() {
		return summonEffects;
	}
	
	public void addDeathEffect(DeathEffect DE) {
		deathEffects.add(DE);
	}
	
	public ArrayList<DeathEffect> getDeathEffects() {
		return deathEffects;
	}

	
	@SuppressWarnings("unchecked")
	public MyTurnState play(BoardState oldstate) {
		ArrayList<Minion> newMySide = (ArrayList<Minion>) (oldstate.getMySide()).clone();
		
		if(newMySide.size()>mypos) newMySide.add(mypos,this);
		else newMySide.add(this);
		
		ArrayList<Integer> newPositionsInPlayOrder = (ArrayList<Integer>) (oldstate.getPositionsInPlayOrder()).clone();
		if (mypos<7) {
			for (int position : oldstate.getPositionsInPlayOrder()) {
				if (position < 7 && position >= mypos) {newPositionsInPlayOrder.set((oldstate.getPositionsInPlayOrder()).indexOf(position), position+1); System.out.println(position+" "+newMySide.size());}
			}
		}
		else {
			for (int position : oldstate.getPositionsInPlayOrder()) {
				if (position >= 7 && position >= mypos) newPositionsInPlayOrder.set((oldstate.getPositionsInPlayOrder()).indexOf(position), position+1);
			}
		}
		newPositionsInPlayOrder.add(mypos);
		
		MyTurnState tempstate =  new BoardState(oldstate.getViewType(), oldstate.getHero(),oldstate.getEnemy(),oldstate.getOppSide(),newMySide,newPositionsInPlayOrder,oldstate.getEnemyHandSize());
		tempstate = battleCry(tempstate);
		
		tempstate = tempstate.doSummonEffects(this);
		return tempstate.applyAuras(this);
	}
	
	@SuppressWarnings("unchecked")
	public MyTurnState place(BoardState oldstate) {
		ArrayList<Minion> newMySide = (ArrayList<Minion>) (oldstate.getMySide()).clone();
		
		if(newMySide.size()>mypos) newMySide.add(mypos,this);
		else newMySide.add(this);
		
		ArrayList<Integer> newPositionsInPlayOrder = (ArrayList<Integer>) (oldstate.getPositionsInPlayOrder()).clone();
		if (mypos<7) {
			for (int position : oldstate.getPositionsInPlayOrder()) {
				if (position < 7 && position >= mypos) {newPositionsInPlayOrder.set((oldstate.getPositionsInPlayOrder()).indexOf(position), position+1); System.out.println(position+" "+newMySide.size());}
			}
		}
		else {
			for (int position : oldstate.getPositionsInPlayOrder()) {
				if (position >= 7 && position >= mypos) newPositionsInPlayOrder.set((oldstate.getPositionsInPlayOrder()).indexOf(position), position+1);
			}
		}
		newPositionsInPlayOrder.add(mypos);
		
		MyTurnState tempstate =  new BoardState(oldstate.getViewType(),oldstate.getHero(),oldstate.getEnemy(),oldstate.getOppSide(),newMySide,newPositionsInPlayOrder,oldstate.getEnemyHandSize());
	
		tempstate = tempstate.doSummonEffects(this);
		return tempstate.applyAuras(this);
	}
	
	@SuppressWarnings("unchecked")
	public MyTurnState attackWith(BoardState oldstate, Minion target) {
		ArrayList<Minion> newMySide = (ArrayList<Minion>) (oldstate.getMySide()).clone();
		
		Minion newAttacker = new Minion(this);
		
		newAttacker.setAttacksTaken(newAttacker.getAttacksTaken()+1);
		newMySide.set(mypos,newAttacker);
		
		BoardState tempstate = new BoardState(oldstate.getViewType(),oldstate.getHero(),oldstate.getEnemy(),oldstate.getOppSide(),newMySide,oldstate.getPositionsInPlayOrder(),oldstate.getEnemyHandSize());
		
		ArrayList<Minion> minions = new ArrayList<Minion>();
		ArrayList<Integer> amounts = new ArrayList<Integer>();
		
		minions.add(newAttacker);
		minions.add(target);
		
		amounts.add(target.getAtk());
		amounts.add(newAttacker.getAtk());
		
		return tempstate.simultaneousDamage(new ArrayList<Hero>(),minions,amounts);
	}
	
	@SuppressWarnings("unchecked")
	public MyTurnState attackHero(BoardState oldstate, Hero target) {
		ArrayList<Minion> newMySide = (ArrayList<Minion>) (oldstate.getMySide()).clone();
		
		Minion newAttacker = new Minion(this);
		
		newAttacker.setAttacksTaken(newAttacker.getAttacksTaken()+1);
		newMySide.set(mypos, newAttacker);
		
		BoardState tempstate = new BoardState(oldstate.getViewType(),oldstate.getHero(),oldstate.getEnemy(),oldstate.getOppSide(),newMySide,oldstate.getPositionsInPlayOrder(),oldstate.getEnemyHandSize());
		
		System.out.println(getAtk()+" "+tempstate.getEnemy().getHP());
		if (target.getMyPos()==14) return (tempstate.getHero()).damage(tempstate,newAttacker.getAtk());
		else return (tempstate.getEnemy()).damage(tempstate,newAttacker.getAtk());
	}
	
	@SuppressWarnings("unchecked")
	public MyTurnState damage(BoardState oldstate, int amount) {
		Minion newMinion = new Minion(this);
		
		if (mypos < 7) {
			ArrayList<Minion> newMySide = (ArrayList<Minion>) (oldstate.getMySide()).clone();
			
			if (divineshield) newMinion.setDivineShield(false);
			else newMinion.setDamageTaken(newMinion.getDamageTaken()+amount);
			
			if (newMinion.getDamageTaken() >= newMinion.getMaxHP()) return destroy(oldstate);
			
			newMySide.set(mypos, newMinion);
			
			return new BoardState(oldstate.getViewType(),oldstate.getHero(),oldstate.getEnemy(),oldstate.getOppSide(),newMySide,oldstate.getPositionsInPlayOrder(),oldstate.getEnemyHandSize());
		}
		else {
			ArrayList<Minion> newOppSide = (ArrayList<Minion>) (oldstate.getOppSide()).clone();
			
			if (divineshield) newMinion.setDivineShield(false);
			else newMinion.setDamageTaken(newMinion.getDamageTaken()+amount);
			
			if (newMinion.getDamageTaken() >= newMinion.getMaxHP()) return destroy(oldstate);
			
			newOppSide.set(mypos-7, newMinion);
			
			return new BoardState(oldstate.getViewType(),oldstate.getHero(),oldstate.getEnemy(),newOppSide,oldstate.getMySide(),oldstate.getPositionsInPlayOrder(),oldstate.getEnemyHandSize());
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public MyTurnState destroy(BoardState oldstate) {
		
		ArrayList<Integer> removed = new ArrayList<Integer>();
		removed.add(mypos);
		
		ArrayList<Integer> newPositionsInPlayOrder = (ArrayList<Integer>) (oldstate.getPositionsInPlayOrder()).clone();
		newPositionsInPlayOrder.removeAll(removed);
		
		if (mypos<7) {
			for (int position : oldstate.getPositionsInPlayOrder()) {
				if (position < 7 && position > mypos) newPositionsInPlayOrder.set((oldstate.getPositionsInPlayOrder()).indexOf(position), position-1);
			}
		}
		else {
			for (int position : oldstate.getPositionsInPlayOrder()) {
				if (position >= 7 && position > mypos) newPositionsInPlayOrder.set((oldstate.getPositionsInPlayOrder()).indexOf(position), position-1);
			}
		}
		
		if (mypos < 7) {
			ArrayList<Minion> newMySide = (ArrayList<Minion>) (oldstate.getMySide()).clone();
			
			newMySide.remove(this);
			
			MyTurnState tempstate =  new BoardState(oldstate.getViewType(),oldstate.getHero(),oldstate.getEnemy(),oldstate.getOppSide(),newMySide,newPositionsInPlayOrder,oldstate.getEnemyHandSize());
			tempstate = deathRattle(tempstate);
			return tempstate.doDeathEffects(this);
		}
		else {
			ArrayList<Minion> newOppSide = (ArrayList<Minion>) (oldstate.getOppSide()).clone();
			
			newOppSide.remove(this);
			
			MyTurnState tempstate = new BoardState(oldstate.getViewType(),oldstate.getHero(),oldstate.getEnemy(),newOppSide,oldstate.getMySide(),newPositionsInPlayOrder,oldstate.getEnemyHandSize());
			tempstate = deathRattle(tempstate);
			return tempstate.doDeathEffects(this);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public MyTurnState heal(BoardState oldstate, int amount) {
		Minion newMinion = new Minion(this);
		
		if (mypos < 7) {
			ArrayList<Minion> newMySide = (ArrayList<Minion>) (oldstate.getMySide()).clone();
			
			if (amount >= damageTaken) newMinion.setDamageTaken(0);
			else newMinion.setDamageTaken(newMinion.getDamageTaken()-amount);
			
			newMySide.set(mypos, newMinion);
			
			return new BoardState(oldstate.getViewType(),oldstate.getHero(),oldstate.getEnemy(),oldstate.getOppSide(),newMySide,oldstate.getPositionsInPlayOrder(),oldstate.getEnemyHandSize());
		}
		else {
			ArrayList<Minion> newOppSide = (ArrayList<Minion>) (oldstate.getOppSide()).clone();
			
			if (amount >= damageTaken) newMinion.setDamageTaken(0);
			else newMinion.setDamageTaken(newMinion.getDamageTaken()-amount);
			
			newOppSide.set(mypos-7, newMinion);
			
			return new BoardState(oldstate.getViewType(),oldstate.getHero(),oldstate.getEnemy(),newOppSide,oldstate.getMySide(),oldstate.getPositionsInPlayOrder(),oldstate.getEnemyHandSize());
		}
	}
	
	@SuppressWarnings("unchecked")
	public Minion applyBuff(Buff buff) {
		Minion newMinion = new Minion(this);
		
		ArrayList<Buff> newBuffs = (ArrayList<Buff>) (newMinion.getBuffs()).clone();
	    newBuffs.add(buff);
	    newMinion.setBuffs(newBuffs);
	    
	    buff.apply(newMinion);
 		
 		return newMinion;
	}
	
	@SuppressWarnings("unchecked")
	public Minion removeBuff(double id) {
		Minion newMinion = new Minion(this);
		
		ArrayList<Buff> newBuffs = (ArrayList<Buff>) (newMinion.getBuffs()).clone();
	    
		for (int i = newBuffs.size()-1; i>=0; i--) {
			if ((newBuffs.get(i)).getID()==id) {
				(newBuffs.get(i)).remove(newMinion);
				newBuffs.remove(i);
				newMinion.setBuffs(newBuffs);
				return newMinion;
			}
		}
 		return newMinion;
	}
	
	@SuppressWarnings("unchecked")
	public Minion applyTempBuff(Buff buff) {
		Minion newMinion = new Minion(this);
		
		ArrayList<Buff> newTempBuffs = (ArrayList<Buff>) (newMinion.getTempBuffs()).clone();
	    newTempBuffs.add(buff);
	    newMinion.setBuffs(newTempBuffs);
	    
	    buff.apply(newMinion);
 		
 		return newMinion;
	}
	
	@SuppressWarnings("unchecked")
	public Minion removeTempBuff(double id) {
		Minion newMinion = new Minion(this);
		
		ArrayList<Buff> newTempBuffs = (ArrayList<Buff>) (newMinion.getTempBuffs()).clone();
	    
		for (int i = newTempBuffs.size()-1; i>=0; i--) {
			if ((newTempBuffs.get(i)).getID()==id) {
				(newTempBuffs.get(i)).remove(newMinion);
				newTempBuffs.remove(i);
				newMinion.setBuffs(newTempBuffs);
				return newMinion;
			}
		}
 		return newMinion;
	}
	
	@SuppressWarnings("unchecked")
	public Minion removeBuffs() {
		Minion newMinion = new Minion(this);
		
		ArrayList<Buff> newBuffs = (ArrayList<Buff>) (newMinion.getBuffs()).clone();
		ArrayList<Buff> newTempBuffs = (ArrayList<Buff>) (newMinion.getTempBuffs()).clone();
		
		for (int i = newBuffs.size()-1 ; i>=0; i--) {
			(newBuffs.get(i)).remove(newMinion);
			newBuffs.remove(i);
		}
		
		for (int i = newTempBuffs.size()-1 ; i>=0; i--) {
			(newTempBuffs.get(i)).remove(newMinion);
			newTempBuffs.remove(i);
		}

			
	    newMinion.setBuffs(newBuffs);
	    newMinion.setTempBuffs(newTempBuffs);
 		
 		return newMinion;
	}
	
	@SuppressWarnings("unchecked")
	public Minion removeTempBuffs() {
		Minion newMinion = new Minion(this);
	
		ArrayList<Buff> newTempBuffs = (ArrayList<Buff>) (newMinion.getTempBuffs()).clone();
		
		for (int i = newTempBuffs.size()-1 ; i>=0; i--) {
			(newTempBuffs.get(i)).remove(newMinion);
			newTempBuffs.remove(i);
		}
		
	    newMinion.setTempBuffs(newTempBuffs);
 		
 		return newMinion;
	}
        
	@SuppressWarnings("unchecked")
	public MyTurnState silence(BoardState oldstate) {
		ArrayList<Minion> newMySide = (ArrayList<Minion>) (oldstate.getMySide()).clone();
	    Minion newMinion = new Minion(this);
	 		
	    newMySide.set(mypos,newMinion);
	    MyTurnState tempstate = new BoardState(oldstate.getViewType(),oldstate.getHero(),oldstate.getEnemy(),oldstate.getOppSide(),oldstate.getMySide(),oldstate.getPositionsInPlayOrder(),oldstate.getEnemyHandSize());
	         
	    newMinion = newMinion.removeBuffs();
	    
	    newMinion.setCharge(false);
		 newMinion.setDivineShield(false);
		 newMinion.setTaunt(false);
		 newMinion.setStealth(false);
		 newMinion.setMaxAttacks(1);
		 newMinion.setSpelldamage(0);
		 newMinion.setFrozen(false);
		 newMinion.setImmune(false);
	 		
	    newMinion.setBattlecrys(new ArrayList<Battlecry>());
	    newMinion.setDeathrattles(new ArrayList<Deathrattle>());
		newMinion.setInspires(new ArrayList<Inspire>());
			
		tempstate = oldstate.removeAuras(newMinion);
			
		newMinion.setAuras(new ArrayList<Aura>());
		newMinion.setSummonEffects(new ArrayList<SummonEffect>());
		newMinion.setDeathEffects(new ArrayList<DeathEffect>());
		newMinion.setStartTurnEffects(new ArrayList<StartTurnEffect>());
		newMinion.setEndTurnEffects(new ArrayList<EndTurnEffect>());
			
		//Perform twice to offset removal above
		tempstate = tempstate.applyAuras(newMinion);
		tempstate = tempstate.applyAuras(newMinion);
			
		return tempstate;
	}
	
	public void setAuras(ArrayList<Aura> auras) {
		this.auras = auras;
	}

	public void setSummonEffects(ArrayList<SummonEffect> summonEffects) {
		this.summonEffects = summonEffects;
	}

	public void setDeathEffects(ArrayList<DeathEffect> deathEffects) {
		this.deathEffects = deathEffects;
	}

	public MyTurnState battleCry(MyTurnState oldstate) {
		MyTurnState tempstate = oldstate;
		for (Battlecry battlecry : battlecrys) {
			tempstate =  battlecry.trigger(this,tempstate);
		}
		return tempstate;
	}
	
	public MyTurnState deathRattle(MyTurnState oldstate) {
		MyTurnState tempstate = oldstate;
		for (Deathrattle deathrattle : deathrattles) {
			tempstate =  deathrattle.trigger(this,tempstate);
		}
		return tempstate;
	}
	
	public MyTurnState inspire(MyTurnState oldstate) {
		MyTurnState tempstate = oldstate;
		for (Inspire inspire : inspires) {
			tempstate = (BoardState) inspire.trigger(this,tempstate);
		}
		return tempstate;
	}
	
	@Override
	public boolean equals(Object that) {
		Minion other = (Minion)that;
		if (name != other.getName()) return false;
		if (mypos != other.getMyPos()) return false;
		if (cost != other.getCost()) return false;
		if (atk != other.getAtk()) return false;
		if (damageTaken != other.getDamageTaken()) return false;
		if (maxHP != other.getMaxHP()) return false;
		if (summonSickness != other.isSummonSickness()) return false;
		if (charge != other.isCharge()) return false;
		if (divineshield != other.isDivineShield()) return false;
		if (taunt != other.isTaunt()) return false;
		if (stealth != other.isStealth()) return false;
		if (attacksTaken != other.getAttacksTaken()) return false;
		if (maxAttacks != other.getMaxAttacks()) return false;
		if (frozen != other.isFrozen()) return false;
		if (immune != other.isImmune()) return false;
		return true;
	}
	
	public int getAttacksTaken() {
		return attacksTaken;
	}

	public void setAttacksTaken(int attacksTaken) {
		this.attacksTaken = attacksTaken;
	}

	public int getMaxAttacks() {
		return maxAttacks;
	}

	public void setMaxAttacks(int maxAttacks) {
		this.maxAttacks = maxAttacks;
	}

	public Race getRace() {
		return race;
	}

	public void setRace(Race race) {
		this.race = race;
	}

	public int getSpelldamage() {
		return spelldamage;
	}

	public void setSpelldamage(int spelldamage) {
		this.spelldamage = spelldamage;
	}

	@Override
	public MyTurnState playCard(BoardState oldstate, Character target) {
		Minion newMinion = new Minion(this);
		if (target.getMyPos()==14) newMinion.setMyPos(0);
		else newMinion.setMyPos(target.getMyPos()+1);
		
		return newMinion.play(oldstate);
	}

	@Override
	public CardType getType() {
		return CardType.MINION;
	}
	
	public void setImmune(boolean b) {
		immune = b;
	}

	@Override
	public boolean isImmune() {
		return immune;
	}

	public double getId() {
		return id;
	}

	public void setId(double id) {
		this.id = id;
	}

}
