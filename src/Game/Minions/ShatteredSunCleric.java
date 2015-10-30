package Game.Minions;

import Game.Battlecrys.ChooseTargetBuffBC;
import Game.Buffs.AdditiveBuff;

public class ShatteredSunCleric extends Minion {

	public ShatteredSunCleric() {
		super("Shattered Sun Cleric",-1,3,3,2);
		battlecrys.add(new ChooseTargetBuffBC(new AdditiveBuff(-1,1,1,0)));
	}
	
	public ShatteredSunCleric(Minion m) {
		super(m);
	}
	
}