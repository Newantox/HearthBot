package Game.Minions;

import Game.BoardState;

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
	}
	
	public Minion(int target) {
		this.setMyPos(target);
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
	
	public BoardState play(BoardState oldstate) {
		Minion[] newMySide = new Minion[7];
		for (int i = 0; i<mypos; i++) newMySide[i] = oldstate.getMySide()[i];
		newMySide[mypos] = this;
		for (int i = mypos+1; i<7; i++) newMySide[i] = oldstate.getMySide()[i-1];
		BoardState tempstate =  new BoardState(oldstate.getHero(),oldstate.getEnemy(),oldstate.getCurrentMana(),oldstate.getTotalMana(),oldstate.getOppSide(),newMySide,oldstate.getMyDeck(),oldstate.getMyHand());
		return this.battleCry(tempstate);
	}
	
	public BoardState damage(BoardState oldstate, int amount) {
		Minion newMinion = new Minion(this);
		if (mypos < 7) {
			Minion[] newMySide = new Minion[7];
			for (int i = 0; i<7; i++) {
				if (oldstate.getMySide()[i] != null) newMySide[i] = oldstate.getMySide()[i];
			}
			newMySide[mypos] = newMinion;
			if (divineshield) newMinion.setDivineShield(false);
			else if (amount >= hp) newMinion.destroy(oldstate);
			else newMinion.setHP(newMinion.getHP()-amount);
			return new BoardState(oldstate.getHero(),oldstate.getEnemy(),oldstate.getCurrentMana(),oldstate.getTotalMana(),oldstate.getOppSide(),newMySide,oldstate.getMyDeck(),oldstate.getMyHand());
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
			return new BoardState(oldstate.getHero(),oldstate.getEnemy(),oldstate.getCurrentMana(),oldstate.getTotalMana(),newOppSide,oldstate.getMySide(),oldstate.getMyDeck(),oldstate.getMyHand());
		}
		
	}
	
	public BoardState destroy(BoardState oldstate) {
		if (mypos < 7) {
			Minion[] newMySide = new Minion[7];
			for (int i = 0; i<7; i++) {
				if (oldstate.getMySide()[i] != null) newMySide[i] = oldstate.getMySide()[i];
			}
			for (int i = mypos; i<7; i++) {
				if(i>=6) newMySide[i] = null;
				else newMySide[i] = newMySide[i+1];
			}
			return new BoardState(oldstate.getHero(),oldstate.getEnemy(),oldstate.getCurrentMana(),oldstate.getTotalMana(),oldstate.getOppSide(),newMySide,oldstate.getMyDeck(),oldstate.getMyHand());
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
			return new BoardState(oldstate.getHero(),oldstate.getEnemy(),oldstate.getCurrentMana(),oldstate.getTotalMana(),newOppSide,oldstate.getMySide(),oldstate.getMyDeck(),oldstate.getMyHand());
		}
		
	}
	
	public BoardState battleCry(BoardState oldstate) {
		return oldstate;
	}
	
	public BoardState deathRattle(BoardState oldstate) {
		return oldstate;
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
		return true;
	}
}
