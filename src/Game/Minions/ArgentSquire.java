package Game.Minions;

public class ArgentSquire extends Minion {
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

	public ArgentSquire(int target) {
		super(target);
		this.name = "Argent Squire";
		this.cost = 1;
		this.atk = 1;
		this.hp = 1;
		this.maxHP = 1;
		this.ready = false;
		this.charge = false;
		this.divineshield = true;
		this.taunt = false;
		this.stealth = false;
	}
	
	public ArgentSquire(Minion m) {
		super(m);
	}


}