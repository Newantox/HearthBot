package Game.Minions;

public class FlametongueTotem extends Minion {

	public FlametongueTotem(int target) {
		super("Flametongue Totem",target,2,0,3);
		setRace(Race.TOTEM);
		auras.add(new FlametongueTotemAura());
	}
	
	public FlametongueTotem(Minion m) {
		super(m);
	}
	
}