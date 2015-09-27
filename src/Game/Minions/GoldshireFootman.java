package Game.Minions;

public class GoldshireFootman extends Minion {

	public GoldshireFootman(int target) {
		super("Goldshire Footman",target,1,1,2);
		setTaunt(true);
	}
	
	public GoldshireFootman(Minion m) {
		super(m);
	}
	
}