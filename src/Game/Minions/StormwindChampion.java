package Game.Minions;

import Game.Auras.StormwindChampionAura;

public class StormwindChampion extends Minion {

	public StormwindChampion() {
		super("Stormwind Champion",7,6,6);
		auras.add(new StormwindChampionAura());
	}
	
	public StormwindChampion(Minion m) {
		super(m);
	}
	
}
