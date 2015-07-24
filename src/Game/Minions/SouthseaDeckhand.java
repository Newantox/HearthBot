package Game.Minions;

public class SouthseaDeckhand extends Minion {
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

	public SouthseaDeckhand(int target) {
		super(target);
		this.name = "Southsea Deckhand";
		this.cost = 1;
		this.atk = 2;
		this.hp = 1;
		this.maxHP = 1;
		this.ready = false;
		this.charge = false;
		this.divineshield = false;
		this.taunt = false;
		this.stealth = false;
	}
	
	public SouthseaDeckhand(Minion m) {
		super(m);
	}

}
