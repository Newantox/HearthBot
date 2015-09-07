package Game.Minions;

public class ShieldedMinibot extends Minion {

	public ShieldedMinibot(int target) {
		super("Shielded Minibot",target,2,2,2,2);
		setRace(Race.MECH);
		setDivineShield(true);
	}
	
	public ShieldedMinibot(Minion m) {
		super(m);
	}

}
