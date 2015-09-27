package Game.Minions;

public class SearingTotem extends Minion {

	public SearingTotem(int target) {
		super("Searing Totem",target,1,1,1);
		setRace(Race.TOTEM);
	}
	
	public SearingTotem(Minion m) {
		super(m);
	}
	
}