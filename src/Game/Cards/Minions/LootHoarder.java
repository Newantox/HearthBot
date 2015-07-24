package Game.Cards.Minions;

public class LootHoarder extends MinionCard {
	private String name;
	private int cost;
	private int hp;
	private int atk;
	private boolean charge;
	private boolean divineshield;
	private boolean stealth;

	public LootHoarder() {
		name = "Loot Hoarder";
		cost = 1;
		hp = 1;
		atk = 2;
		charge = false;
		divineshield = false;
		stealth = false;
	}	
}