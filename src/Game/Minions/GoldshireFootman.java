package Game.Minions;

public class GoldshireFootman extends Minion {

	public GoldshireFootman() {
		super("Goldshire Footman",1,1,2);
		setTaunt(true);
	}
	
	public GoldshireFootman(Minion m) {
		super(m);
	}
	
}