package Game.Weapons;

import java.util.ArrayList;

import Game.BoardState;
import Game.CardType;
import Game.Character;
import Game.MyTurnState;
import Game.PlayableCard;
import Game.TargetsType;
import Game.Battlecrys.Battlecry;
import Game.Deathrattles.Deathrattle;
import Game.Heroes.Hero;
import Game.Inspires.Inspire;
import Game.Minions.Minion;

public class Weapon implements PlayableCard {
	private int id;
	private String name;
	private int cost;
	private int atk;
	private int durability;
	
	protected ArrayList<Battlecry> battlecrys;
	protected ArrayList<Deathrattle> deathrattles;
	protected ArrayList<Inspire> inspires;
	
	public Weapon(String name, int cost, int atk, int durability) {
		this.name = name;
		this.cost = cost;
		this.atk = atk;
		this.setDurability(durability);
		
		this.battlecrys = new ArrayList<Battlecry>();
		this.deathrattles = new ArrayList<Deathrattle>();
		this.inspires = new ArrayList<Inspire>();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Weapon(Weapon w) {
		this.id = w.getId();
			this.name = w.getName();
			this.cost = w.getCost();
			this.atk = w.getAtk();
			this.durability = w.getDurability();
			
			this.battlecrys = w.getBattlecrys();
			this.deathrattles = w.getDeathrattles();
			this.inspires = w.getInspires();
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
	
	public MyTurnState attackWith(BoardState oldstate, Hero defender) {
		return oldstate.heroAttack(id,defender);
	}
	
	public MyTurnState attackWith(BoardState oldstate, Minion defender) {
		return oldstate.heroAttack(id,defender);
	}
	
	public MyTurnState battleCry(MyTurnState oldstate) {
		MyTurnState tempstate = oldstate;
		for (Battlecry battlecry : battlecrys) {
			tempstate = battlecry.trigger(this,tempstate);
		}
		return tempstate;
	}
	
	public MyTurnState deathRattle(MyTurnState oldstate, TargetsType side) {
		MyTurnState tempstate = oldstate;
		for (Deathrattle deathrattle : deathrattles) {
			tempstate = deathrattle.trigger(tempstate,this,side);
		}
		return tempstate;
	}
	
	public MyTurnState inspire(BoardState oldstate) {
		MyTurnState tempstate = oldstate;
		for (Inspire inspire : inspires) {
			tempstate = inspire.trigger(this,tempstate);
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

	@Override
	public MyTurnState playCard(BoardState oldstate, Character target) {
		return (oldstate.getHero()).equipWeapon(oldstate, this);
	}
	
	public void playPrint(Character target) {
		System.out.println("Play "+getName());
	}
	
	public String playOutput(Character target) {
		return ("Play "+getName());
	}

	@Override
	public CardType getType() {
		return CardType.WEAPON;
	} 
	
}
