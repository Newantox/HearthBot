package Game.Minions;

public class MurlocScout extends Minion {
	
	public MurlocScout(int target) {
		super("Murloc Scout",target,0,1,1);
		setRace(Race.MURLOC);
	}
	
	public MurlocScout(Minion m) {
		super(m);
	}
	
}
