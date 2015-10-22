package Game.Minions;

public class BloodfenRaptor extends Minion {

	public BloodfenRaptor() {
		super("Bloodfen Raptor",-1,2,3,2);
		setRace(Race.BEAST);
	}
	
	public BloodfenRaptor(Minion m) {
		super(m);
	}
	
}