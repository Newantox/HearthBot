package Game.Cards.Minions;

public class WolfRider extends MinionCard {
	private String name;
	private int cost;
	private int hp;
	private int atk;
	private boolean charge;

	public WolfRider() {
		name = "Wolf Rider";
		cost = 3;
		hp = 1;
		atk = 3;
		charge = true;
	}

}
