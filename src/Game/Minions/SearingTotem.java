package Game.Minions;

public class SearingTotem extends Minion {

	public SearingTotem() {
		super("Searing Totem",-1,1,1,1);
		setRace(Race.TOTEM);
	}
	
	public SearingTotem(Minion m) {
		super(m);
	}
	
}