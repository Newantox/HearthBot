package Game.Minions;

public class Wisp extends Minion {

	public Wisp(int target) {
		super("Wisp",target,0,1,1);
	}
	
	public Wisp(Minion m) {
		super(m);
	}

}
