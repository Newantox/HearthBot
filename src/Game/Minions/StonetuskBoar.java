package Game.Minions;

public class StonetuskBoar extends Minion {

	public StonetuskBoar(int target) {
		super("Stonetusk Boar",target,1,1,1);
		setRace(Race.BEAST);
		setCharge(true);
	}
	
	public StonetuskBoar(Minion m) {
		super(m);
	}
	
}
