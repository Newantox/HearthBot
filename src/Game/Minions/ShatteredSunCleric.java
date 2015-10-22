package Game.Minions;

import Game.Battlecrys.ShatteredSunClericBC;

public class ShatteredSunCleric extends Minion {

	public ShatteredSunCleric() {
		super("Shattered Sun Cleric",-1,3,3,2);
		battlecrys.add(new ShatteredSunClericBC());
	}
	
	public ShatteredSunCleric(Minion m) {
		super(m);
	}
	
}