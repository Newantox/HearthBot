package Game.Heroes;

import Game.BoardState;
import Game.Heroes.HeroPowers.HeroPower;
import Game.Weapons.Weapon;

public class Hero {
	private String name;
	private int myPos;
	private int HP;
	private int maxHP;
	private int armour;
	private int currentMana;
	private int totalMana;
	private int overload;
	private Weapon weapon;
	private boolean ready;
	private boolean powerUsed;
	
	private HeroPower power;
	
	public Hero(String name, int mypos, int HP, int maxHP, int Armour, int currentMana, int totalMana, int overload, Weapon weapon) {
		this.setName(name);
		this.myPos = mypos;
		this.HP = HP;
		this.maxHP = maxHP;
		this.armour = Armour;
		this.currentMana = currentMana;
		this.totalMana = totalMana;
		this.overload = overload;
		this.weapon = weapon;
		this.ready = true;
		this.powerUsed = false;
	}

	public Hero(Hero h) {
		this.name = h.getName();
		this.myPos = h.getMyPos();
		this.HP = h.getHP();
		this.armour = h.getArmour();
		this.currentMana = h.getCurrentMana();
		this.totalMana = h.getTotalMana();
		this.overload = h.getOverload();
		this.weapon = h.getWeapon();
		this.ready = h.isReady();
		this.powerUsed = h.getPowerUsed();
		this.power = h.getHeroPower();
	}
	
	public int getCurrentMana() {
		return currentMana;
	}

	public void setCurrentMana(int currentMana) {
		this.currentMana = currentMana;
	}

	public int getTotalMana() {
		return totalMana;
	}

	public void setTotalMana(int totalMana) {
		this.totalMana = totalMana;
	}

	public int getOverload() {
		return overload;
	}

	public void setOverload(int overload) {
		this.overload = overload;
	}

	public Hero fresh() {
		return new Hero(this);
	}
	
	public BoardState damage(BoardState oldstate, int amount) {
		Hero hero = this.fresh();
		if (hero.getArmour()>=amount) hero.setArmour(hero.getArmour()-2);
		else {int additional = amount - hero.getArmour(); hero.setArmour(0); hero.setHP(hero.getHP()-additional);}
		if (hero.getMyPos() == 14) return new BoardState(hero,oldstate.getEnemy(),oldstate.getOppSide(),oldstate.getMySide(),oldstate.getMyDeck(),oldstate.getMyHand(),oldstate.getSummonEffects(),oldstate.getEnemyHandSize());
		else return new BoardState(oldstate.getHero(),hero,oldstate.getOppSide(),oldstate.getMySide(),oldstate.getMyDeck(),oldstate.getMyHand(),oldstate.getSummonEffects(),oldstate.getEnemyHandSize());
	}
	
	public BoardState heal(BoardState oldstate, int amount) {
		Hero hero = this.fresh();
		hero.setHP(Math.min(hero.getHP()+amount,hero.getMaxHP()));
		
		if (hero.getMyPos() == 14) return new BoardState(hero,oldstate.getEnemy(),oldstate.getOppSide(),oldstate.getMySide(),oldstate.getMyDeck(),oldstate.getMyHand(),oldstate.getSummonEffects(),oldstate.getEnemyHandSize());
		else return new BoardState(oldstate.getHero(),hero,oldstate.getOppSide(),oldstate.getMySide(),oldstate.getMyDeck(),oldstate.getMyHand(),oldstate.getSummonEffects(),oldstate.getEnemyHandSize());
	}
	
	public BoardState useMana(BoardState oldstate, int amount) {
		Hero hero = this.fresh();
		hero.setCurrentMana(hero.getCurrentMana()-amount);
		return new BoardState(hero,oldstate.getEnemy(),oldstate.getOppSide(),oldstate.getMySide(),oldstate.getMyDeck(),oldstate.getMyHand(),oldstate.getSummonEffects(),oldstate.getEnemyHandSize());
	}

	public boolean canAttack() {
		return (weapon!=null && ready && this.getWeapon().getDurability()>0);
	}
	
	public int getMyPos() {
		return myPos;
	}

	public void setMyPos(int myPos) {
		this.myPos = myPos;
	}

	public int getHP() {
		return HP;
	}

	public void setHP(int HP) {
		this.HP = HP;
	}
	
	public int getMaxHP() {
		return maxHP;
	}

	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}
	
	public int getArmour() {
		return armour;
	}

	public void setArmour(int armour) {
		this.armour = armour;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
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
	
	public boolean getPowerUsed() {
		return powerUsed;
	}
	
	public void setPowerUsed(boolean b) {
		powerUsed = b;
	}

	public boolean isAttackable() {
		return true;
	}
	
	public HeroPower getHeroPower() {
		return power;
	}
	
	public void setHeroPower(HeroPower power) {
		this.power = power;
	} 
	
	public BoardState equipWeapon(BoardState oldstate, Weapon weapon) {
		Hero hero = this.fresh();
		BoardState tempstate = weapon.battleCry(oldstate);
		hero.setWeapon(weapon);
		if (myPos==14) return new BoardState(hero,tempstate.getEnemy(),tempstate.getOppSide(),tempstate.getMySide(),tempstate.getMyDeck(),tempstate.getMyHand(),tempstate.getSummonEffects(),tempstate.getEnemyHandSize());
		else return new BoardState(tempstate.getHero(),hero,tempstate.getOppSide(),tempstate.getMySide(),tempstate.getMyDeck(),tempstate.getMyHand(),tempstate.getSummonEffects(),tempstate.getEnemyHandSize());
	}
	
	public BoardState destroyWeapon(BoardState oldstate) {
		Hero hero = this.fresh();
		Weapon weapon = hero.getWeapon();
		BoardState tempstate = weapon.deathRattle(oldstate);
		hero.setWeapon(null);
		if (myPos==14) return new BoardState(hero,tempstate.getEnemy(),tempstate.getOppSide(),tempstate.getMySide(),tempstate.getMyDeck(),tempstate.getMyHand(),tempstate.getSummonEffects(),tempstate.getEnemyHandSize());
		else return new BoardState(tempstate.getHero(),hero,tempstate.getOppSide(),tempstate.getMySide(),tempstate.getMyDeck(),tempstate.getMyHand(),tempstate.getSummonEffects(),tempstate.getEnemyHandSize());
	}
	
	@Override
	public boolean equals(Object that) {
		Hero other = (Hero)that;
		if (name != other.getName()) return false;
		if (HP != other.getHP()) return false;
		if (armour != other.getArmour()) return false;
		if (currentMana != other.getCurrentMana()) return false;
		if (totalMana != other.getTotalMana()) return false;
		if (overload != other.getOverload()) return false;
		if (weapon==null && other.getWeapon() != null) return false;
		if (weapon!= null && other.getWeapon() == null) return false;
		if (weapon==null && other.getWeapon() == null);
		else if (!weapon.equals(other.getWeapon())) return false;
		if (ready != other.isReady()) return false;
		return true;
	}
}
