package Game.Minions;

import Game.EndTurnEffects.HealingTotemET;

public class HealingTotem extends Minion {

	public HealingTotem() {
		super("Healing Totem",1,0,2);
		setRace(Race.TOTEM);
		endTurnEffects.add(new HealingTotemET());
	}
	
	public HealingTotem(Minion m) {
		super(m);
	}
	
}