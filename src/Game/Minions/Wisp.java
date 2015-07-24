package Game.Minions;

public class Wisp extends Minion {
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

	public Wisp(int target) {
		super(target);
		this.name = "Wisp";
		this.cost = 0;
		this.atk = 1;
		this.hp = 1;
		this.maxHP = 1;
		this.ready = true;
		this.charge = false;
		this.divineshield = false;
		this.taunt = false;
		this.stealth = false;
	}
	
	public Wisp(Minion m) {
		super(m);
	}

}
