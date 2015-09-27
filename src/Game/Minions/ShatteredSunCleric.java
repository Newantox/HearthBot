package Game.Minions;

import Game.Battlecrys.ShatteredSunClericBC;

public class ShatteredSunCleric extends Minion {

	public ShatteredSunCleric(int target) {
		super("Shattered Sun Cleric",target,3,3,2);
		battlecrys.add(new ShatteredSunClericBC());
	}
	
	public ShatteredSunCleric(Minion m) {
		super(m);
	}
	
}