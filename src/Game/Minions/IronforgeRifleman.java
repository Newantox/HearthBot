package Game.Minions;

import Game.Battlecrys.ChooseTargetDamageBC;

public class IronforgeRifleman extends Minion {

	public IronforgeRifleman() {
		super("Ironforge Rifleman",3,2,2);
		battlecrys.add(new ChooseTargetDamageBC(1));
	}
	
	public IronforgeRifleman(Minion m) {
		super(m);
	}
	
}