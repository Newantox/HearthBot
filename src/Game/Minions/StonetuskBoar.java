package Game.Minions;

public class StonetuskBoar extends Minion {

	public StonetuskBoar() {
		super("Stonetusk Boar",1,1,1);
		setRace(Race.BEAST);
		setCharge(true);
	}
	
	public StonetuskBoar(Minion m) {
		super(m);
	}
	
}
