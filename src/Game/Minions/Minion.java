package Game.Minions;

import java.util.ArrayList;

import Game.BoardState;
import Game.Battlecrys.MinionBattlecry;
import Game.Deathrattles.MinionDeathrattle;
import Game.Inspires.Inspire;
import Game.SummonEffects.SummonEffect;
import Search.State;

public class Minion {
	private String name;
	private int mypos;
	private int cost;
	private int atk;
	private int hp;
	private int maxHP;
	private boolean ready;
	private boolean charge;
	private boolean divineshield;
	private boolean taunt;
	private boolean stealth;
	private boolean frozen;
	
	private int tempHPChange;
	private int tempAtkChange;
	
	protected ArrayList<MinionBattlecry> battlecrys;
	protected ArrayList<MinionDeathrattle> deathrattles;
	protected ArrayList<Inspire> inspires;
	
	protected ArrayList<SummonEffect> summonEffects;
	
	public Minion (String name, int mypos, int cost, int atk, int hp, int maxHP) {
		this.name = name;
		this.mypos = mypos;
		this.cost = cost;
		this.atk = atk;
		this.hp = hp;
		this.maxHP = maxHP;
		this.ready = false;
		this.charge = false;
		this.divineshield = false;
		this.taunt = false;
		this.stealth = false;
		this.setFrozen(false);
		this.tempHPChange = 0;
		this.tempAtkChange = 0;
		
		this.battlecrys = new ArrayList<MinionBattlecry>();
		this.deathrattles = new ArrayList<MinionDeathrattle>();
		this.inspires = new ArrayList<Inspire>();
		
		this.summonEffects = new ArrayList<SummonEffect>();
	}
	
	public Minion(Minion m) {
		this.setName(m.getName());
		this.setMyPos(m.getMyPos());
		this.setCost(m.getCost());
		this.setAtk(m.getAtk());
		this.setHP(m.getHP());
		this.setReady(m.isReady());
		this.setCharge(m.isCharge());
		this.setDivineShield(m.isDivineShield());
		this.setTaunt(m.isTaunt());
		this.setStealth(m.isStealth());
		this.setFrozen(m.isFrozen());
		
		this.setTempHPChange(m.getTempHPChange());
		this.setTempAtkChange(m.getTempAtkChange());
		
		this.battlecrys = m.getBattlecrys();
		this.deathrattles = m.getDeathrattles();
		this.inspires = m.getInspires();
		
		this.summonEffects = m.getSummonEffects();
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
		return (ready && (atk > 0));
	}
	
	public boolean isAttackable() {
		return true;
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
	
	public void addSummonEffect(SummonEffect SE) {
		summonEffects.add(SE);
	}
	
	public ArrayList<SummonEffect> getSummonEffects() {
		return summonEffects;
	}
	
	public State play(BoardState oldstate) {
		Minion[] newMySide = new Minion[7];
		for (int i = 0; i<mypos; i++) newMySide[i] = oldstate.getMySide()[i];
		newMySide[mypos] = this;
		for (int i = mypos+1; i<7; i++) newMySide[i] = oldstate.getMySide()[i-1];
		
		ArrayList<SummonEffect> newSummonEffects = oldstate.getSummonEffects();
		if (summonEffects!=null) newSummonEffects.addAll(summonEffects);
		
		State tempstate =  new BoardState(oldstate.getHero(),oldstate.getEnemy(),oldstate.getOppSide(),newMySide,oldstate.getMyDeck(),oldstate.getMyHand(),newSummonEffects,oldstate.getEnemyHandSize());
		tempstate = battleCry((BoardState) tempstate);
		
		for (SummonEffect effect : oldstate.getSummonEffects()) {
			tempstate = effect.perform(tempstate, this);
		}
		
		return tempstate;
	}
	
	public State place(BoardState oldstate) {
		Minion[] newMySide = new Minion[7];
		for (int i = 0; i<mypos; i++) newMySide[i] = oldstate.getMySide()[i];
		newMySide[mypos] = this;
		for (int i = mypos+1; i<7; i++) newMySide[i] = oldstate.getMySide()[i-1];
		
		ArrayList<SummonEffect> newSummonEffects = oldstate.getSummonEffects();
		newSummonEffects.addAll(summonEffects);
		
		State tempstate =  new BoardState(oldstate.getHero(),oldstate.getEnemy(),oldstate.getOppSide(),newMySide,oldstate.getMyDeck(),oldstate.getMyHand(),newSummonEffects,oldstate.getEnemyHandSize());
		
		for (SummonEffect effect : oldstate.getSummonEffects()) {
			tempstate = effect.perform(tempstate, this);
		}
		
		return tempstate;
	}
	
	public State attackWith(BoardState oldstate, Minion target) {
		Minion[] newMySide = new Minion[7];
		for (int i = 0; i<7; i++) {
			newMySide[i] = oldstate.getMySide()[i];
		}
		
		Minion newAttacker = new Minion(this);
		newAttacker.setReady(false);
		newMySide[mypos] = newAttacker;
		
		BoardState tempstate = new BoardState(oldstate.getHero(),oldstate.getEnemy(),oldstate.getOppSide(),newMySide,oldstate.getMyDeck(),oldstate.getMyHand(),oldstate.getSummonEffects(),oldstate.getEnemyHandSize());
		
		ArrayList<Minion> minions = new ArrayList<Minion>();
		ArrayList<Integer> amounts = new ArrayList<Integer>();
		
		minions.add(newAttacker);
		minions.add(target);
		
		amounts.add(target.getAtk());
		amounts.add(newAttacker.getAtk());
		
		return tempstate.simultaneousDamage(minions,amounts);
	}
	
	public State damage(BoardState oldstate, int amount) {
		Minion newMinion = new Minion(this);
		if (mypos < 7) {
			Minion[] newMySide = new Minion[7];
			for (int i = 0; i<7; i++) {
				if (oldstate.getMySide()[i] != null) newMySide[i] = oldstate.getMySide()[i];
			}
			newMySide[mypos] = newMinion;
			if (divineshield) newMinion.setDivineShield(false);
			else if (amount >= hp) return newMinion.destroy(oldstate);
			else newMinion.setHP(newMinion.getHP()-amount);
			return new BoardState(oldstate.getHero(),oldstate.getEnemy(),oldstate.getOppSide(),newMySide,oldstate.getMyDeck(),oldstate.getMyHand(),oldstate.getSummonEffects(),oldstate.getEnemyHandSize());
		}
		else {
			Minion[] newOppSide = new Minion[7];
			for (int i = 0; i<7; i++) {
				if (oldstate.getOppSide()[i] != null) newOppSide[i] = oldstate.getOppSide()[i];
			}
			if (divineshield) newMinion.setDivineShield(false);
			else if (amount >= hp) return newMinion.destroy(oldstate);
			else newMinion.setHP(newMinion.getHP()-amount);
			newOppSide[mypos-7] = newMinion;
			return new BoardState(oldstate.getHero(),oldstate.getEnemy(),newOppSide,oldstate.getMySide(),oldstate.getMyDeck(),oldstate.getMyHand(),oldstate.getSummonEffects(),oldstate.getEnemyHandSize());
		}
		
	}
	
	public State destroy(BoardState oldstate) {
		if (mypos < 7) {
			Minion[] newMySide = new Minion[7];
			for (int i = 0; i<7; i++) {
				if (oldstate.getMySide()[i] != null) newMySide[i] = oldstate.getMySide()[i];
			}
			for (int i = mypos; i<7; i++) {
				if(i>=6) newMySide[i] = null;
				else newMySide[i] = newMySide[i+1];
			}
			BoardState tempstate =  new BoardState(oldstate.getHero(),oldstate.getEnemy(),oldstate.getOppSide(),newMySide,oldstate.getMyDeck(),oldstate.getMyHand(),oldstate.getSummonEffects(),oldstate.getEnemyHandSize());
			return deathRattle(tempstate);
		}
		else {
			Minion[] newOppSide = new Minion[7];
			for (int i = 0; i<7; i++) {
				if (oldstate.getOppSide()[i] != null) newOppSide[i] = oldstate.getOppSide()[i];
			}
			for (int i = mypos-7; i<7; i++) {
				if(i>=6) newOppSide[i] = null;
				else newOppSide[i] = newOppSide[i+1];
			}
			BoardState tempstate = new BoardState(oldstate.getHero(),oldstate.getEnemy(),newOppSide,oldstate.getMySide(),oldstate.getMyDeck(),oldstate.getMyHand(),oldstate.getSummonEffects(),oldstate.getEnemyHandSize());
			return deathRattle(tempstate);
		}
		
	}
	
	public BoardState changeAtk(BoardState oldstate, int amount) {
		Minion defender = new Minion(this);
		if (getMyPos()<7) {
			Minion[] newMySide = new Minion[7];
			for (int i = 0; i<7; i++) {
				if (oldstate.getMySide()[i] != null && i!=getMyPos()) newMySide[i] = oldstate.getMySide()[i];
			}
			newMySide[getMyPos()] = defender;
			
			defender.setAtk(defender.getAtk() + amount);
		
			return new BoardState(oldstate.getHero(),oldstate.getEnemy(),oldstate.getOppSide(),newMySide,oldstate.getMyDeck(),oldstate.getMyHand(),oldstate.getSummonEffects(),oldstate.getEnemyHandSize());
		}
		
		else {
			defender = oldstate.getOppSide()[getMyPos()-7];
		    Minion[] newOppSide = new Minion[7];
		    for (int i = 0; i<7; i++) {
		    	if (oldstate.getOppSide()[i] != null && i!=getMyPos()-7) newOppSide[i] = oldstate.getOppSide()[i];
		    }
		    newOppSide[getMyPos()-7] = defender;
		    
		    defender.setAtk(defender.getAtk() + amount);
			
		    return new BoardState(oldstate.getHero(),oldstate.getEnemy(),newOppSide,oldstate.getMySide(),oldstate.getMyDeck(),oldstate.getMyHand(),oldstate.getSummonEffects(),oldstate.getEnemyHandSize());
		}
	}
	
	public BoardState changeHP(BoardState oldstate, int amount) {
		Minion defender = new Minion(this);
		if (getMyPos()<7) {
			Minion[] newMySide = new Minion[7];
			for (int i = 0; i<7; i++) {
				if (oldstate.getMySide()[i] != null && i!=getMyPos()) newMySide[i] = oldstate.getMySide()[i];
			}
			newMySide[getMyPos()] = defender;
			
			defender.setHP(defender.getHP() + amount);
		
			return new BoardState(oldstate.getHero(),oldstate.getEnemy(),oldstate.getOppSide(),newMySide,oldstate.getMyDeck(),oldstate.getMyHand(),oldstate.getSummonEffects(),oldstate.getEnemyHandSize());
		}
		
		else {
			defender = oldstate.getOppSide()[getMyPos()-7];
		    Minion[] newOppSide = new Minion[7];
		    for (int i = 0; i<7; i++) {
		    	if (oldstate.getOppSide()[i] != null && i!=getMyPos()-7) newOppSide[i] = oldstate.getOppSide()[i];
		    }
		    newOppSide[getMyPos()-7] = defender;
		    
		    defender.setHP(defender.getHP() + amount);
			
		    return new BoardState(oldstate.getHero(),oldstate.getEnemy(),newOppSide,oldstate.getMySide(),oldstate.getMyDeck(),oldstate.getMyHand(),oldstate.getSummonEffects(),oldstate.getEnemyHandSize());
		}
	}
	
	public State battleCry(BoardState oldstate) {
		State tempstate = oldstate;
		for (MinionBattlecry battlecry : battlecrys) {
			tempstate =  battlecry.trigger(this,tempstate);
		}
		return tempstate;
	}
	
	public State deathRattle(BoardState oldstate) {
		State tempstate = oldstate;
		for (MinionDeathrattle deathrattle : deathrattles) {
			tempstate =  deathrattle.trigger(this,tempstate);
		}
		return tempstate;
	}
	
	public State inspire(BoardState oldstate) {
		BoardState tempstate = oldstate;
		for (Inspire inspire : inspires) {
			tempstate = (BoardState) inspire.perform(this,tempstate);
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

}
