package Game.Minions;

import Game.EndTurnEffects.RagnarosET;

public class Ragnaros extends Minion {

	public Ragnaros() {
		super("Ragnaros",8,8,8);
		endTurnEffects.add(new RagnarosET());
	}
	
	public Ragnaros(Minion m) {
		super(m);
	}
	
}
