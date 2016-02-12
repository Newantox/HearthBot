package Game.Minions;

public class BluegillWarrior extends Minion {
	
	public BluegillWarrior() {
		super("Bluegill Warrior",2,2,1);
		setRace(Race.MURLOC);
		setCharge(true);
	}
	
	public BluegillWarrior(Minion m) {
		super(m);
	}
	
}
