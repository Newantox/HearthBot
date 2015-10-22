package Game.Minions;

import Game.Battlecrys.AcidicSwampOozeBC;

public class AcidicSwampOoze extends Minion {
	
	public AcidicSwampOoze() {
		super("Acidic Swamp Ooze",-1,2,3,2);
		battlecrys.add(new AcidicSwampOozeBC());
		
	}
	
	public AcidicSwampOoze(Minion m) {
		super(m);
	}
	

}
