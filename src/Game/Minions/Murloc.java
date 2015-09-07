package Game.Minions;

public class Murloc extends Minion {
	
	public Murloc(int target) {
		super("Murloc",target,1,1,1,1);
		setRace(Race.MURLOC);
	}
	
	public Murloc(Minion m) {
		super(m);
	}

}
