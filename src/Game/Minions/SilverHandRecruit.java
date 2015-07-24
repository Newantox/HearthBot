package Game.Minions;

public class SilverHandRecruit extends Minion {
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

	public SilverHandRecruit(int target) {
		super(target);
		this.name = "Silver Hand Recruit";
		this.cost = 1;
		this.atk = 1;
		this.hp = 1;
		this.maxHP = 1;
		this.ready = false;
		this.charge = false;
		this.divineshield = false;
		this.taunt = false;
		this.stealth = false;
	}
	
	public SilverHandRecruit(Minion m) {
		super(m);
	}

}