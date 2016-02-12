package Game.Minions;

public class FlametongueTotem extends Minion {

	public FlametongueTotem() {
		super("Flametongue Totem",2,0,3);
		setRace(Race.TOTEM);
		auras.add(new FlametongueTotemAura());
	}
	
	public FlametongueTotem(Minion m) {
		super(m);
	}
	
}