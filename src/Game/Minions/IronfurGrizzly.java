package Game.Minions;

public class IronfurGrizzly extends Minion {

	public IronfurGrizzly() {
		super("Ironfur Grizzly",-1,3,3,3);
		setRace(Race.BEAST);
		setTaunt(true);
	}
	
	public IronfurGrizzly(Minion m) {
		super(m);
	}
	
}