package Game.Weapons;

import Game.BoardState;
import Game.Heroes.Hero;
import Game.Minions.Minion;

public class Weapon {
	private String name;
	private int cost;
	private int atk;
	private int durability;
	
	public Weapon(String name, int cost, int atk, int durability) {
		this.name = name;
		this.cost = cost;
		this.atk = atk;
		this.setDurability(durability);
	}
	
	public Weapon(Weapon w) {
			this.name = w.getName();
			this.cost = w.getCost();
			this.atk = w.getAtk();
			this.durability = w.getDurability();
	}
	
	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAtk() {
		return atk;
	}
	
	public void setAtk(int atk) {
		this.atk = atk;
	}

	public int getDurability() {
		return durability;
	}

	public void setDurability(int durability) {
		this.durability = durability;
	}
	
	public Weapon fresh() {
		return new Weapon(this);
	}
	
	public BoardState attackWith(BoardState oldstate, Minion defender) {
		Hero hero = (oldstate.getHero()).fresh();
		BoardState tempstate = defender.damage(oldstate,atk);
		tempstate = hero.damage(tempstate,defender.getAtk());
		hero.setReady(false);
		tempstate =  new BoardState(hero,tempstate.getEnemy(),tempstate.getOppSide(),tempstate.getMySide(),tempstate.getMyDeck(),tempstate.getMyHand(),tempstate.getSummonEffects(),tempstate.getEnemyHandSize());
		return changeDurability(tempstate,14,-1);
	}
	
	public BoardState attackWith(BoardState oldstate, Hero defender) {
		Hero hero = (oldstate.getHero()).fresh();
		BoardState tempstate = defender.damage(oldstate,atk);
		hero.setReady(false);
		tempstate =  new BoardState(hero,tempstate.getEnemy(),tempstate.getOppSide(),tempstate.getMySide(),tempstate.getMyDeck(),tempstate.getMyHand(),tempstate.getSummonEffects(),tempstate.getEnemyHandSize());
		return changeDurability(tempstate,14,-1);
	}
	
	public BoardState changeDurability(BoardState oldstate, int side, int amount) {
		if (side==14) {
			Hero hero = (oldstate.getHero()).fresh();
			Weapon weapon = this.fresh();
			weapon.setDurability(weapon.getDurability() + amount);
			if (weapon.getDurability()<=0) return hero.destroyWeapon(oldstate);
			else {
				hero.setWeapon(weapon);
				return new BoardState(hero,oldstate.getEnemy(),oldstate.getOppSide(),oldstate.getMySide(),oldstate.getMyDeck(),oldstate.getMyHand(),oldstate.getSummonEffects(),oldstate.getEnemyHandSize());
			}
		}
		else {
			Hero hero = (oldstate.getEnemy()).fresh();
			Weapon weapon = this.fresh();
			weapon.setDurability(weapon.getDurability() + amount);
			if (weapon.getDurability()<=0) return hero.destroyWeapon(oldstate);
			else {
				hero.setWeapon(weapon);
				return new BoardState(oldstate.getHero(),hero,oldstate.getOppSide(),oldstate.getMySide(),oldstate.getMyDeck(),oldstate.getMyHand(),oldstate.getSummonEffects(),oldstate.getEnemyHandSize());
			}
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
		Weapon other = (Weapon)that;
		if (name != other.getName()) return false;
		if (cost != other.getCost()) return false;
		if (atk != other.getAtk()) return false;
		if (durability != other.getDurability()) return false;
		return true;
	} 
	
}
