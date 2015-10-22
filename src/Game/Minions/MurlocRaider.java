package Game.Minions;

public class MurlocRaider extends Minion {

	public MurlocRaider() {
		super("Murloc Raider",-1,1,2,1);
		setRace(Race.MURLOC);
	}
	
	public MurlocRaider(Minion m) {
		super(m);
	}
	
}
