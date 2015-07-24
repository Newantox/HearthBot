package Game.Minions;

public class ShieldedMinibot extends Minion {
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

	public ShieldedMinibot(int target) {
		super(target);
		this.name = "Shielded Minibot";
		this.cost = 2;
		this.atk = 2;
		this.hp = 2;
		this.maxHP = 2;
		this.ready = false;
		this.charge = false;
		this.divineshield = true;
		this.taunt = false;
		this.stealth = false;
	}
	
	public ShieldedMinibot(Minion m) {
		super(m);
	}

}
