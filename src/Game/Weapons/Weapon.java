package Game.Weapons;

import java.util.ArrayList;

import Game.BoardState;
import Game.MyTurnState;
import Game.Battlecrys.WeaponBattlecry;
import Game.Deathrattles.WeaponDeathrattle;
import Game.Heroes.Hero;
import Game.Inspires.WeaponInspire;
import Game.Minions.Minion;

public class Weapon {
	private String name;
	private int cost;
	private int atk;
	private int durability;
	
	protected ArrayList<WeaponBattlecry> battlecrys;
	protected ArrayList<WeaponDeathrattle> deathrattles;
	protected ArrayList<WeaponInspire> inspires;
	
	public Weapon(String name, int cost, int atk, int durability) {
		this.name = name;
		this.cost = cost;
		this.atk = atk;
		this.setDurability(durability);
		
		this.battlecrys = new ArrayList<WeaponBattlecry>();
		this.deathrattles = new ArrayList<WeaponDeathrattle>();
		this.inspires = new ArrayList<WeaponInspire>();
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

	public ArrayList<WeaponInspire> getInspires() {
		return inspires;
	}

	public void setInspires(ArrayList<WeaponInspire> inspires) {
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
	
	public MyTurnState attackWith(BoardState oldstate, Minion defender) {
		Hero hero = (oldstate.getHero()).fresh();
		hero.setReady(false);
		BoardState tempstate1 = hero.damage(oldstate,defender.getAtk());
		MyTurnState tempstate2 = defender.damage(tempstate1,atk);
		if (hero.getMyPos()==14) return tempstate2.changeHeroWeaponAtkDurability(0,-1);
		else return tempstate2.changeEnemyWeaponAtkDurability(0,-1);
	}
	
	public MyTurnState attackWith(BoardState oldstate, Hero defender) {
		Hero hero = (oldstate.getHero()).fresh();
		BoardState tempstate = defender.damage(oldstate,atk);
		hero.setReady(false);
		tempstate =  new BoardState(tempstate.getViewType(),hero,tempstate.getEnemy(),tempstate.getOppSide(),tempstate.getMySide(),tempstate.getPositionsInPlayOrder(),tempstate.getEnemyHandSize());
		if (hero.getMyPos()==14) return tempstate.changeHeroWeaponAtkDurability(0,-1);
		else return tempstate.changeEnemyWeaponAtkDurability(0,-1);
	}
	
	public MyTurnState battleCry(MyTurnState oldstate) {
		MyTurnState tempstate = oldstate;
		for (WeaponBattlecry battlecry : battlecrys) {
			tempstate = battlecry.trigger(tempstate);
		}
		return tempstate;
	}
	
	public MyTurnState deathRattle(MyTurnState oldstate) {
		MyTurnState tempstate = oldstate;
		for (WeaponDeathrattle deathrattle : deathrattles) {
			tempstate = deathrattle.trigger(tempstate);
		}
		return tempstate;
	}
	
	public MyTurnState inspire(BoardState oldstate) {
		MyTurnState tempstate = oldstate;
		for (WeaponInspire inspire : inspires) {
			tempstate = inspire.trigger(tempstate);
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
