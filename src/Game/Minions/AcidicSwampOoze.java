package Game.Minions;

import Game.BoardState;
import Game.Heroes.Hero;

public class AcidicSwampOoze extends Minion {
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
	
	public AcidicSwampOoze(Minion m) {
		super(m);
	}

	public AcidicSwampOoze(int target) {
		super(target);
		this.name = "Acidic Swamp Ooze";
		this.cost = 2;
		this.atk = 3;
		this.hp = 2;
		this.maxHP = 2;
		this.ready = false;
		this.charge = false;
		this.divineshield = false;
		this.taunt = false;
		this.stealth = false;
	}
	
	@Override
	public BoardState battleCry(BoardState oldstate) {
		Hero newenemy = new Hero(oldstate.getEnemy());
		return newenemy.destroyWeapon(oldstate);
	}
	

}
