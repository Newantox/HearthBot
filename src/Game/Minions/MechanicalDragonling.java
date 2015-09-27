package Game.Minions;

public class MechanicalDragonling extends Minion {

	public MechanicalDragonling(int target) {
		super("Mechanical Dragonling",target,1,2,1);
		setRace(Race.MECH);
	}
	
	public MechanicalDragonling(Minion m) {
		super(m);
	}
	
}