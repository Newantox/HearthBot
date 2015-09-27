package Game.Minions;

import Game.Battlecrys.AcidicSwampOozeBC;

public class AcidicSwampOoze extends Minion {
	
	public AcidicSwampOoze(int target) {
		super("Acidic Swamp Ooze",target,2,3,2);
		battlecrys.add(new AcidicSwampOozeBC());
		
	}
	
	public AcidicSwampOoze(Minion m) {
		super(m);
	}
	

}
