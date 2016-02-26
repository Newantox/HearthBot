package Game.Minions;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

import Game.Character;
import Game.BoardState;
import Game.CardType;
import Game.ChoiceState;
import Game.MyTurnState;
import Game.TargetsType;
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
	private int id;
	private String name;
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
	
	public Minion (String name, int cost, int atk,int maxHP) {
		this.id = 0;
		this.name = name;
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
		if (oldstate.findPosition(id)<7) return (taunt || !oldstate.isTauntOnSide(oldstate.getMySide()));
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
	public MyTurnState play(BoardState oldstate, int position) {
		if (oldstate.getMySide().size()<7) {
			Minion newMinion = new Minion(this);
			ArrayList<Minion> newMySide = (ArrayList<Minion>) (oldstate.getMySide()).clone();
			
			newMinion.setId(oldstate.getNextId());
			
			if(newMySide.size()>position) newMySide.add(position,newMinion);
			else newMySide.add(newMinion);
			
			ArrayList<Integer> newIdsInPlayOrder = (ArrayList<Integer>) (oldstate.getIdsInPlayOrder()).clone();
			newIdsInPlayOrder.add(newMinion.getId());
			
			MyTurnState tempstate =  new BoardState(oldstate.getViewType(), oldstate.getHero(),oldstate.getEnemy(),oldstate.getOppSide(),newMySide,newIdsInPlayOrder,oldstate.getEnemyHandSize(),oldstate.isTurnEnded(),newMinion.getId());
			
			tempstate = newMinion.battleCry(tempstate);
			
			if (tempstate.getClass().equals((new ChoiceState()).getClass())) return tempstate;
			else {
				tempstate = tempstate.doSummonEffects(newMinion.getId());
				return tempstate.applyAuras(newMinion.getId());
			}
		}
		else return oldstate;
	}
	
	@SuppressWarnings("unchecked")
	public MyTurnState place(BoardState oldstate, int position) {
		Minion newMinion = new Minion(this);
		
		newMinion.setId(oldstate.getNextId());
		
		if (position<7 && oldstate.getMySide().size()<7) {
			ArrayList<Minion> newMySide = (ArrayList<Minion>) (oldstate.getMySide()).clone();
			
			if(newMySide.size()>position) newMySide.add(position,newMinion);
			else newMySide.add(newMinion);
			
			ArrayList<Integer> newIdsInPlayOrder = (ArrayList<Integer>) (oldstate.getIdsInPlayOrder()).clone();
			newIdsInPlayOrder.add(newMinion.getId());
			
			MyTurnState tempstate =  new BoardState(oldstate.getViewType(),oldstate.getHero(),oldstate.getEnemy(),oldstate.getOppSide(),newMySide,newIdsInPlayOrder,oldstate.getEnemyHandSize(),oldstate.isTurnEnded(),newMinion.getId());
			
			tempstate = tempstate.doSummonEffects(newMinion.getId());
			return tempstate.applyAuras(newMinion.getId());
		}
		
		else if (position>=7 && oldstate.getOppSide().size()<7) { 
			ArrayList<Minion> newOppSide = (ArrayList<Minion>) (oldstate.getOppSide()).clone();
			
			if(newOppSide.size()>position-7) newOppSide.add(position-7,newMinion);
			else newOppSide.add(newMinion);
			
			ArrayList<Integer> newIdsInPlayOrder = (ArrayList<Integer>) (oldstate.getIdsInPlayOrder()).clone();
			newIdsInPlayOrder.add(newMinion.getId());
			
			MyTurnState tempstate =  new BoardState(oldstate.getViewType(),oldstate.getHero(),oldstate.getEnemy(),newOppSide,oldstate.getMySide(),newIdsInPlayOrder,oldstate.getEnemyHandSize(),oldstate.isTurnEnded(),newMinion.getId());
			
			tempstate = tempstate.doSummonEffects(newMinion.getId());
			return tempstate.applyAuras(newMinion.getId());
		}
		else return oldstate;
	}
	
	@SuppressWarnings("unchecked")
	public MyTurnState attackWith(BoardState oldstate, Minion target) {
		ArrayList<Minion> newMySide = (ArrayList<Minion>) (oldstate.getMySide()).clone();
		ArrayList<Minion> newOppSide = (ArrayList<Minion>) (oldstate.getOppSide()).clone();
		
		Minion newAttacker = new Minion(this);
		
		newAttacker.setAttacksTaken(newAttacker.getAttacksTaken()+1);
		if (oldstate.findPosition(newAttacker.getId())<7) newMySide.set(oldstate.findPosition(newAttacker.getId()),newAttacker);
		else newOppSide.set(oldstate.findPosition(newAttacker.getId())-7, newAttacker);
		
		BoardState tempstate = new BoardState(oldstate.getViewType(),oldstate.getHero(),oldstate.getEnemy(),newOppSide,newMySide,oldstate.getIdsInPlayOrder(),oldstate.getEnemyHandSize(),oldstate.isTurnEnded(),oldstate.getIdCounter());
		
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
		newMySide.set(oldstate.findPosition(newAttacker.getId()), newAttacker);
		
		BoardState tempstate = new BoardState(oldstate.getViewType(),oldstate.getHero(),oldstate.getEnemy(),oldstate.getOppSide(),newMySide,oldstate.getIdsInPlayOrder(),oldstate.getEnemyHandSize(),oldstate.isTurnEnded(),oldstate.getIdCounter());
		
		if (target.getSide().equals(TargetsType.ALLYCHAR)) return (tempstate.getHero()).damage(tempstate,newAttacker.getAtk());
		else return (tempstate.getEnemy()).damage(tempstate,newAttacker.getAtk());
	}
	
	@SuppressWarnings("unchecked")
	public MyTurnState damage(BoardState oldstate, int amount) {
		Minion newMinion = new Minion(this);
		
		if (oldstate.findPosition(newMinion.getId())<7) {
			ArrayList<Minion> newMySide = (ArrayList<Minion>) (oldstate.getMySide()).clone();
			
			if (divineshield) newMinion.setDivineShield(false);
			else newMinion.setDamageTaken(newMinion.getDamageTaken()+amount);
			
			if (newMinion.getDamageTaken() >= newMinion.getMaxHP()) return destroy(oldstate);
			
			newMySide.set(oldstate.findPosition(newMinion.getId()), newMinion);
			
			return new BoardState(oldstate.getViewType(),oldstate.getHero(),oldstate.getEnemy(),oldstate.getOppSide(),newMySide,oldstate.getIdsInPlayOrder(),oldstate.getEnemyHandSize(),oldstate.isTurnEnded(),oldstate.getIdCounter());
		}
		else {
			ArrayList<Minion> newOppSide = (ArrayList<Minion>) (oldstate.getOppSide()).clone();
			
			if (divineshield) newMinion.setDivineShield(false);
			else newMinion.setDamageTaken(newMinion.getDamageTaken()+amount);
			
			if (newMinion.getDamageTaken() >= newMinion.getMaxHP()) return destroy(oldstate);
			
			newOppSide.set(oldstate.findPosition(newMinion.getId())-7, newMinion);
			
			return new BoardState(oldstate.getViewType(),oldstate.getHero(),oldstate.getEnemy(),newOppSide,oldstate.getMySide(),oldstate.getIdsInPlayOrder(),oldstate.getEnemyHandSize(),oldstate.isTurnEnded(),oldstate.getIdCounter());
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public MyTurnState destroy(BoardState oldstate) {
		TargetsType side;
		if (oldstate.findPosition(id)<7) side = TargetsType.ALLYMINIONS;
		else side = TargetsType.ENEMYMINIONS;
	
		ArrayList<Integer> newIdsInPlayOrder = (ArrayList<Integer>) (oldstate.getIdsInPlayOrder()).clone();
		for (int i = 0; i<newIdsInPlayOrder.size(); i++) {
			if (newIdsInPlayOrder.get(i)==id) newIdsInPlayOrder.remove(i);
		}
		
		if (oldstate.findPosition(id)<7) {
			ArrayList<Minion> newMySide = (ArrayList<Minion>) (oldstate.getMySide()).clone();
			
			newMySide.remove(this);
			
			MyTurnState tempstate =  new BoardState(oldstate.getViewType(),oldstate.getHero(),oldstate.getEnemy(),oldstate.getOppSide(),newMySide,newIdsInPlayOrder,oldstate.getEnemyHandSize(),oldstate.isTurnEnded(),oldstate.getIdCounter());
			
			tempstate = tempstate.doDeathEffects(this,side);
			return deathRattle(tempstate,side);
		}
		else {
			ArrayList<Minion> newOppSide = (ArrayList<Minion>) (oldstate.getOppSide()).clone();
			
			newOppSide.remove(this);
			
			MyTurnState tempstate = new BoardState(oldstate.getViewType(),oldstate.getHero(),oldstate.getEnemy(),newOppSide,oldstate.getMySide(),newIdsInPlayOrder,oldstate.getEnemyHandSize(),oldstate.isTurnEnded(),oldstate.getIdCounter());
			
			tempstate = tempstate.doDeathEffects(this,side);
			return deathRattle(tempstate,side);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public MyTurnState heal(BoardState oldstate, int amount) {
		Minion newMinion = new Minion(this);
		
		if (oldstate.findPosition(id)<7) {
			ArrayList<Minion> newMySide = (ArrayList<Minion>) (oldstate.getMySide()).clone();
			
			if (amount >= damageTaken) newMinion.setDamageTaken(0);
			else newMinion.setDamageTaken(newMinion.getDamageTaken()-amount);
			
			newMySide.set(oldstate.findPosition(id), newMinion);
			
			return new BoardState(oldstate.getViewType(),oldstate.getHero(),oldstate.getEnemy(),oldstate.getOppSide(),newMySide,oldstate.getIdsInPlayOrder(),oldstate.getEnemyHandSize(),oldstate.isTurnEnded(),oldstate.getIdCounter());
		}
		else {
			ArrayList<Minion> newOppSide = (ArrayList<Minion>) (oldstate.getOppSide()).clone();
			
			if (amount >= damageTaken) newMinion.setDamageTaken(0);
			else newMinion.setDamageTaken(newMinion.getDamageTaken()-amount);
			
			newOppSide.set(oldstate.findPosition(id)-7, newMinion);
			
			return new BoardState(oldstate.getViewType(),oldstate.getHero(),oldstate.getEnemy(),newOppSide,oldstate.getMySide(),oldstate.getIdsInPlayOrder(),oldstate.getEnemyHandSize(),oldstate.isTurnEnded(),oldstate.getIdCounter());
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
	public Minion removeBuff(int id) {
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
	    newMinion.setTempBuffs(newTempBuffs);
	    
	    buff.apply(newMinion);
 		
 		return newMinion;
	}
	
	@SuppressWarnings("unchecked")
	public Minion removeTempBuff(int id) {
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
	 		
	    newMySide.set(oldstate.findPosition(id),newMinion);
	    MyTurnState tempstate = new BoardState(oldstate.getViewType(),oldstate.getHero(),oldstate.getEnemy(),oldstate.getOppSide(),oldstate.getMySide(),oldstate.getIdsInPlayOrder(),oldstate.getEnemyHandSize(),oldstate.isTurnEnded(),oldstate.getIdCounter());
	         
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
	
	public MyTurnState deathRattle(MyTurnState oldstate, TargetsType side) {
		MyTurnState tempstate = oldstate;
		for (Deathrattle deathrattle : deathrattles) {
			tempstate =  deathrattle.trigger(tempstate,this,side);
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
		if (that==null) return false;
		Minion other = (Minion)that;
		if (id != other.getId()) return false;
		if (name != other.getName()) return false;
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
		int position;
		
		//if (target==null) position = 0;
		//else position = oldstate.findPosition(((Minion) target).getId())+1;
		position = oldstate.numberOfAlliedMinions();
		
		return newMinion.play(oldstate,position);
	}
	
	public void playPrint(Character target) {
		if (target==null) System.out.println("Summon "+getName());
		else System.out.println("Summon "+getName()+" "+"to right of "+target.getName());
	}
	
	public String playOutput(Character target) {
		if (target==null) return ("Summon "+getName());
		else return ("Summon "+getName()+" "+"to right of "+target.getName());
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
