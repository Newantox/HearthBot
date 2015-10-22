package Game.Minions;

public class MurlocScout extends Minion {
	
	public MurlocScout() {
		super("Murloc Scout",-1,0,1,1);
		setRace(Race.MURLOC);
	}
	
	public MurlocScout(Minion m) {
		super(m);
	}
	
}
