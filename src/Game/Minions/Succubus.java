package Game.Minions;

public class Succubus extends Minion {

	public Succubus(int target) {
		super("Succubus",target,2,4,3);
		setRace(Race.DEMON);
		battlecrys.add(new SuccubusBC());
	}
	
	public Succubus(Minion m) {
		super(m);
	}
	
}