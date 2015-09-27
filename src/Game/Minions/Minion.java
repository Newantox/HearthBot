package Game.Minions;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

import Game.BoardState;
import Game.MyTurnState;
import Game.Auras.Aura;
import Game.Battlecrys.MinionBattlecry;
import Game.DeathEffects.DeathEffect;
import Game.Deathrattles.MinionDeathrattle;
import Game.EndTurnEffects.EndTurnEffect;
import Game.Heroes.Hero;
import Game.Inspires.MinionInspire;
import Game.StartTurnEffects.StartTurnEffect;
import Game.SummonEffects.SummonEffect;

public class Minion {
	private String name;
	private int mypos;
	private int cost;
	private int atk;
	private int hp;
	private int maxHP;
	private Race race;
	private boolean ready;
	private boolean charge;
	private boolean divineshield;
	private boolean taunt;
	private boolean stealth;
	private boolean windfury;
	private int spelldamage;
	private boolean frozen;
	
	private int tempHPChange;
	private int tempAtkChange;
	
	private boolean deadCheck;
	
	protected ArrayList<MinionBattlecry> battlecrys;
	protected ArrayList<MinionDeathrattle> deathrattles;
	protected ArrayList<MinionInspire> inspires;
	
	protected ArrayList<Aura> auras;
	protected ArrayList<SummonEffect> summonEffects;
	protected ArrayList<DeathEffect> deathEffects;
	protected ArrayList<StartTurnEffect> startTurnEffects;
	protected ArrayList<EndTurnEffect> endTurnEffects;
	
	public Minion (String name, int mypos, int cost, int atk,int maxHP) {
		this.name = name;
		this.mypos = mypos;
		this.cost = cost;
		this.atk = atk;
		this.hp = maxHP;
		this.maxHP = maxHP;
		this.setRace(Race.NORMAL);
		this.ready = false;
		this.charge = false;
		this.divineshield = false;
		this.taunt = false;
		this.stealth = false;
		this.windfury = false;
		this.setSpelldamage(0);
		this.frozen = false;
		this.tempHPChange = 0;
		this.tempAtkChange = 0;
		
		this.battlecrys = new ArrayList<MinionBattlecry>();
		this.deathrattles = new ArrayList<MinionDeathrattle>();
		this.inspires = new ArrayList<MinionInspire>();
		
		this.auras = new ArrayList<Aura>();
		this.summonEffects = new ArrayList<SummonEffect>();
		this.deathEffects = new ArrayList<DeathEffect>();
		this.startTurnEffects = new ArrayList<StartTurnEffect>();
		this.endTurnEffects = new ArrayList<EndTurnEffect>();
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
		this.setName(m.getName());
		this.setMyPos(m.getMyPos());
		this.setCost(m.getCost());
		this.setAtk(m.getAtk());
		this.setHP(m.getHP());
		this.setMaxHP(m.getMaxHP());
		this.setRace(m.getRace());
		this.setReady(m.isReady());
		this.setCharge(m.isCharge());
		this.setDivineShield(m.isDivineShield());
		this.setTaunt(m.isTaunt());
		this.setStealth(m.isStealth());
		this.setWindfury(m.getWindfury());
		this.setSpelldamage(m.getSpelldamage());
		this.setFrozen(m.isFrozen());
		
		this.setTempHPChange(m.getTempHPChange());
		this.setTempAtkChange(m.getTempAtkChange());
		
		this.battlecrys = m.getBattlecrys();
		this.deathrattles = m.getDeathrattles();
		this.inspires = m.getInspires();
		
		this.auras = m.getAuras();
		this.summonEffects = m.getSummonEffects();
		this.deathEffects = m.getDeathEffects();
		this.startTurnEffects = m.getStartTurnEffects();
		this.endTurnEffects = m.getEndTurnEffects();
		
	}

	public boolean getWindfury() {
		return windfury;
	}

	public void setWindfury(boolean windfury) {
		this.windfury = windfury;
	}

	public int getTempHPChange() {
		return tempHPChange;
	}

	public void setTempHPChange(int tempHPChange) {
		this.tempHPChange = tempHPChange;
	}

	public int getTempAtkChange() {
		return tempAtkChange;
	}

	public void setTempAtkChange(int tempAtkChange) {
		this.tempAtkChange = tempAtkChange;
	}
	
	public ArrayList<MinionBattlecry> getBattlecrys() {
		return battlecrys;
	}

	public void setBattlecrys(ArrayList<MinionBattlecry> battlecrys) {
		this.battlecrys = battlecrys;
	}

	public ArrayList<MinionDeathrattle> getDeathrattles() {
		return deathrattles;
	}

	public void setDeathrattles(ArrayList<MinionDeathrattle> deathrattles) {
		this.deathrattles = deathrattles;
	}

	public ArrayList<MinionInspire> getInspires() {
		return inspires;
	}

	public void setInspires(ArrayList<MinionInspire> inspires) {
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
		return (ready && (atk > 0));
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

	public int getHP() {
		return hp;
	}

	public void setMaxHP(int max) {
		this.maxHP = max;
	}
	
	public int getMaxHP() {
		return maxHP;
	}

	public void setHP(int hp) {
		this.hp = hp;
	}

	public boolean isReady() {
		return ready;
	}

	public void setReady(boolean ready) {
		this.ready = ready;
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
	
	public void addDeathrattle(MinionDeathrattle DR) {
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
		
		newAttacker.setReady(false);
		newMySide.set(mypos,newAttacker);
		
		BoardState tempstate = new BoardState(oldstate.getViewType(),oldstate.getHero(),oldstate.getEnemy(),oldstate.getOppSide(),newMySide,oldstate.getPositionsInPlayOrder(),oldstate.getEnemyHandSize());
		
		ArrayList<Minion> minions = new ArrayList<Minion>();
		ArrayList<Integer> amounts = new ArrayList<Integer>();
		
		minions.add(newAttacker);
		minions.add(target);
		
		amounts.add(target.getAtk());
		amounts.add(newAttacker.getAtk());
		
		return tempstate.simultaneousDamage(minions,amounts);
	}
	
	@SuppressWarnings("unchecked")
	public MyTurnState attackHero(BoardState oldstate, Hero target) {
		ArrayList<Minion> newMySide = (ArrayList<Minion>) (oldstate.getMySide()).clone();
		
		Minion newAttacker = new Minion(this);
		
		newAttacker.setReady(false);
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
			else if (amount >= hp) destroy(oldstate);
			else newMinion.setHP(newMinion.getHP()-amount);
			
			newMySide.set(mypos, newMinion);
			
			return new BoardState(oldstate.getViewType(),oldstate.getHero(),oldstate.getEnemy(),oldstate.getOppSide(),newMySide,oldstate.getPositionsInPlayOrder(),oldstate.getEnemyHandSize());
		}
		else {
			ArrayList<Minion> newOppSide = (ArrayList<Minion>) (oldstate.getOppSide()).clone();
			
			if (divineshield) newMinion.setDivineShield(false);
			else if (amount >= hp) destroy(oldstate);
			else newMinion.setHP(newMinion.getHP()-amount);
			
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
	
		//deadCheck = true;
		
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
	public BoardState changeAtkHP(BoardState oldstate, int amountAtk, int amountHP) {
		//if (deadCheck) return oldstate;
		
		Minion defender = new Minion(this);
		
		defender.setAtk(defender.getAtk() + amountAtk);
		defender.setHP(defender.getHP() + amountHP);
		defender.setMaxHP(defender.getMaxHP() + amountHP);
		System.out.println("Changing"+defender.getAtk()+" "+oldstate.getEnemy().getHP());
		
		if (mypos<7) {
			ArrayList<Minion> newMySide = (ArrayList<Minion>) (oldstate.getMySide()).clone();
			newMySide.set(mypos,defender);
		
			return new BoardState(oldstate.getViewType(),oldstate.getHero(),oldstate.getEnemy(),oldstate.getOppSide(),newMySide,oldstate.getPositionsInPlayOrder(),oldstate.getEnemyHandSize());
		}
		
		else {
			ArrayList<Minion> newOppSide = (ArrayList<Minion>) (oldstate.getOppSide()).clone();
			newOppSide.set(mypos-7,defender);
			
		    return new BoardState(oldstate.getViewType(),oldstate.getHero(),oldstate.getEnemy(),newOppSide,oldstate.getMySide(),oldstate.getPositionsInPlayOrder(),oldstate.getEnemyHandSize());
		}
	}
	
	@SuppressWarnings("unchecked")
	public BoardState changeAttributes(BoardState oldstate, boolean charge, boolean divineshield, boolean taunt, boolean stealth, boolean windfury, int spelldamage, boolean frozen) {
		ArrayList<Minion> newMySide = (ArrayList<Minion>) (oldstate.getMySide()).clone();
		Minion newMinion = new Minion(this);
		
		newMySide.set(mypos,newMinion);
        BoardState tempstate = new BoardState(oldstate.getViewType(),oldstate.getHero(),oldstate.getEnemy(),oldstate.getOppSide(),oldstate.getMySide(),oldstate.getPositionsInPlayOrder(),oldstate.getEnemyHandSize());
        
        newMinion.setCharge(charge);
 		newMinion.setDivineShield(divineshield);
 		newMinion.setTaunt(taunt);
 		newMinion.setStealth(stealth);
 		newMinion.setWindfury(windfury);
 		newMinion.setSpelldamage(spelldamage);
 		newMinion.setFrozen(frozen);
 		
 		return tempstate;
	}
        
	@SuppressWarnings("unchecked")
	public MyTurnState silence(BoardState oldstate) {
		try
	      {
	        Constructor<? extends Minion> constructor = getClass().getDeclaredConstructor(int.class) ;
	        ArrayList<Minion> newMySide = (ArrayList<Minion>) (oldstate.getMySide()).clone();
	        Minion newMinion = constructor.newInstance(mypos);
	         
	        newMySide.set(mypos,newMinion);
	        MyTurnState tempstate = new BoardState(oldstate.getViewType(),oldstate.getHero(),oldstate.getEnemy(),oldstate.getOppSide(),oldstate.getMySide(),oldstate.getPositionsInPlayOrder(),oldstate.getEnemyHandSize());
	         
	        newMinion.setHP(Math.min(newMinion.getMaxHP(),hp));
	        
	        newMinion.setCharge(false);
	 		newMinion.setDivineShield(false);
	 		newMinion.setTaunt(false);
	 		newMinion.setStealth(false);
	 		newMinion.setWindfury(false);
	 		newMinion.setSpelldamage(0);
	 		newMinion.setFrozen(false);
	 		newMinion.setTempHPChange(0);
	 		newMinion.setTempAtkChange(0);
	 		
	 		newMinion.setBattlecrys(new ArrayList<MinionBattlecry>());
			newMinion.setDeathrattles(new ArrayList<MinionDeathrattle>());
			newMinion.setInspires(new ArrayList<MinionInspire>());
			
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
		catch( Exception e )
	      {
	        return oldstate;
	      }
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
		for (MinionBattlecry battlecry : battlecrys) {
			tempstate =  battlecry.trigger(this,tempstate);
		}
		return tempstate;
	}
	
	public MyTurnState deathRattle(MyTurnState oldstate) {
		MyTurnState tempstate = oldstate;
		for (MinionDeathrattle deathrattle : deathrattles) {
			tempstate =  deathrattle.trigger(this,tempstate);
		}
		return tempstate;
	}
	
	public MyTurnState inspire(MyTurnState oldstate) {
		MyTurnState tempstate = oldstate;
		for (MinionInspire inspire : inspires) {
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
		if (hp != other.getHP()) return false;
		if (maxHP != other.getMaxHP()) return false;
		if (ready != other.isReady()) return false;
		if (charge != other.isCharge()) return false;
		if (divineshield != other.isDivineShield()) return false;
		if (taunt != other.isTaunt()) return false;
		if (stealth != other.isStealth()) return false;
		if (frozen != other.isFrozen()) return false;
		if (tempHPChange != other.getTempHPChange()) return false;
		if (tempAtkChange != other.getTempAtkChange()) return false;
		return true;
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

}
