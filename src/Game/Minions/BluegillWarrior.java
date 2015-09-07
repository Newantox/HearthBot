package Game.Minions;

public class BluegillWarrior extends Minion {
	
	public BluegillWarrior(int target) {
		super("Bluegill Warrior",target,2,2,1,1);
		setRace(Race.MURLOC);
		setCharge(true);
	}
	
	public BluegillWarrior(Minion m) {
		super(m);
	}
	
}
