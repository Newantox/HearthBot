package Game.Minions;

import Game.Battlecrys.IronforgeRiflemanBC;

public class IronforgeRifleman extends Minion {

	public IronforgeRifleman(int target) {
		super("Ironforge Rifleman",target,3,2,2);
		battlecrys.add(new IronforgeRiflemanBC());
	}
	
	public IronforgeRifleman(Minion m) {
		super(m);
	}
	
}