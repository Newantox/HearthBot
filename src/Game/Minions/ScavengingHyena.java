package Game.Minions;

import Game.DeathEffects.ScavengingHyenaDE;

public class ScavengingHyena extends Minion {

	public ScavengingHyena(int target) {
		super("Scavenging Hyena",target,2,2,2);
		setRace(Race.BEAST);
		addDeathEffect(new ScavengingHyenaDE());
	}
	
	public ScavengingHyena(Minion m) {
		super(m);
	}
	
}