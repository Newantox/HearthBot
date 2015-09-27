package Game.Minions;

public class IronfurGrizzly extends Minion {

	public IronfurGrizzly(int target) {
		super("Ironfur Grizzly",target,3,3,3);
		setRace(Race.BEAST);
		setTaunt(true);
	}
	
	public IronfurGrizzly(Minion m) {
		super(m);
	}
	
}