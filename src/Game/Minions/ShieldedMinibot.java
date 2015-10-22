package Game.Minions;

public class ShieldedMinibot extends Minion {

	public ShieldedMinibot() {
		super("Shielded Minibot",-1,2,2,2);
		setRace(Race.MECH);
		setDivineShield(true);
	}
	
	public ShieldedMinibot(Minion m) {
		super(m);
	}

}
