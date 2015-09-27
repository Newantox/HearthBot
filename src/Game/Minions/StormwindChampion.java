package Game.Minions;

import Game.Auras.StormwindChampionAura;

public class StormwindChampion extends Minion {

	public StormwindChampion(int target) {
		super("Stormwind Champion",target,7,6,6);
		auras.add(new StormwindChampionAura());
	}
	
	public StormwindChampion(Minion m) {
		super(m);
	}
	
}
