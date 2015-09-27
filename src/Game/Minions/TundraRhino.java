package Game.Minions;

public class TundraRhino extends Minion {

	public TundraRhino(int target) {
		super("Tundra Rhino",target,5,2,5);
		setRace(Race.BEAST);
		auras.add(new TundraRhinoAura());
	}
	
	public TundraRhino(Minion m) {
		super(m);
	}
	
}