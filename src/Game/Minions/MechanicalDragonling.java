package Game.Minions;

public class MechanicalDragonling extends Minion {

	public MechanicalDragonling() {
		super("Mechanical Dragonling",1,2,1);
		setRace(Race.MECH);
	}
	
	public MechanicalDragonling(Minion m) {
		super(m);
	}
	
}