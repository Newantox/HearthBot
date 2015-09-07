package Game.Minions;

public class MurlocRaider extends Minion {

	public MurlocRaider(int target) {
		super("Murloc Raider",target,1,2,1,1);
		setRace(Race.MURLOC);
	}
	
	public MurlocRaider(Minion m) {
		super(m);
	}
	
}
