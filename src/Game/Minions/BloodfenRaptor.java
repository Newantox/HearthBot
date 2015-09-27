package Game.Minions;

public class BloodfenRaptor extends Minion {

	public BloodfenRaptor(int target) {
		super("Bloodfen Raptor",target,2,3,2);
		setRace(Race.BEAST);
	}
	
	public BloodfenRaptor(Minion m) {
		super(m);
	}
	
}