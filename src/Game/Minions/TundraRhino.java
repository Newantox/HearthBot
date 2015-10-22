package Game.Minions;

public class TundraRhino extends Minion {

	public TundraRhino() {
		super("Tundra Rhino",-1,5,2,5);
		setRace(Race.BEAST);
		auras.add(new TundraRhinoAura());
	}
	
	public TundraRhino(Minion m) {
		super(m);
	}
	
}