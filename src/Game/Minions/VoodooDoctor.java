package Game.Minions;

import Game.Battlecrys.ChooseTargetHealBC;

public class VoodooDoctor extends Minion {

	public VoodooDoctor() {
		super("Voodoo Doctor",1,2,1);
		battlecrys.add(new ChooseTargetHealBC(2));
	}
	
	public VoodooDoctor(Minion m) {
		super(m);
	}
	
}