package Game.Weapons;

import java.util.ArrayList;

import Game.BoardState;
import Game.Battlecrys.WeaponBattlecry;
import Game.Deathrattles.WeaponDeathrattle;
import Game.Heroes.Hero;
import Game.Inspires.Inspire;
import Game.Minions.Minion;
import Search.State;

public class Weapon {
	private String name;
	private int cost;
	private int atk;
	private int durability;
	
	protected ArrayList<WeaponBattlecry> battlecrys;
	protected ArrayList<WeaponDeathrattle> deathrattles;
	protected ArrayList<Inspire> inspires;
	
	public Weapon(String name, int cost, int atk, int durability) {
		this.name = name;
		this.cost = cost;
		this.atk = atk;
		this.setDurability(durability);
		
		this.battlecrys = new ArrayList<WeaponBattlecry>();
		this.deathrattles = new ArrayList<WeaponDeathrattle>();
		this.inspires = new ArrayList<Inspire>();
	}
	
	public Weapon(Weapon w) {
			this.name = w.getName();
			this.cost = w.getCost();
			this.atk = w.getAtk();
			this.durability = w.getDurability();
			
			this.battlecrys = w.getBattlecrys();
			this.deathrattles = w.getDeathrattles();
			this.inspires = w.getInspires();
	}
	
	public ArrayList<WeaponBattlecry> getBattlecrys() {
		return battlecrys;
	}

	public void setBattlecrys(ArrayList<WeaponBattlecry> battlecrys) {
		this.battlecrys = battlecrys;
	}

	public ArrayList<WeaponDeathrattle> getDeathrattles() {
		return deathrattles;
	}

	public void setDeathrattles(ArrayList<WeaponDeathrattle> deathrattles) {
		this.deathrattles = deathrattles;
	}

	public ArrayList<Inspire> getInspires() {
		return inspires;
	}

	public void setInspires(ArrayList<Inspire> inspires) {
		this.inspires = inspires;
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
	
	public State attackWith(BoardState oldstate, Minion defender) {
		Hero hero = (oldstate.getHero()).fresh();
		hero.setReady(false);
		BoardState tempstate1 = hero.damage(oldstate,defender.getAtk());
		State tempstate2 = defender.damage(tempstate1,atk);
		return changeDurability(tempstate2,hero,-1);
	}
	
	public State attackWith(BoardState oldstate, Hero defender) {
		Hero hero = (oldstate.getHero()).fresh();
		BoardState tempstate = defender.damage(oldstate,atk);
		hero.setReady(false);
		tempstate =  new BoardState(hero,tempstate.getEnemy(),tempstate.getOppSide(),tempstate.getMySide(),tempstate.getMyDeck(),tempstate.getMyHand(),tempstate.getSummonEffects(),tempstate.getEnemyHandSize());
		return changeDurability(tempstate,hero,-1);
	}
	
	public State changeDurability(State oldstate, Hero target, int amount) {
		return oldstate.changeWeaponDurability(target,amount);
	}
	
	public State battleCry(BoardState oldstate) {
		BoardState tempstate = oldstate;
		for (WeaponBattlecry battlecry : battlecrys) {
			tempstate = (BoardState) battlecry.perform(this,tempstate);
		}
		return tempstate;
	}
	
	public State deathRattle(BoardState oldstate) {
		BoardState tempstate = oldstate;
		for (WeaponDeathrattle deathrattle : deathrattles) {
			tempstate = (BoardState) deathrattle.perform(this,tempstate);
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
		Weapon other = (Weapon)that;
		if (name != other.getName()) return false;
		if (cost != other.getCost()) return false;
		if (atk != other.getAtk()) return false;
		if (durability != other.getDurability()) return false;
		return true;
	} 
	
}
