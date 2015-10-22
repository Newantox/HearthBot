package Game.Minions;

import Game.Battlecrys.GuardianOfKingsBC;

public class GuardianOfKings extends Minion {

	public GuardianOfKings() {
		super("Guardian Of Kings",-1,7,5,6);
		battlecrys.add(new GuardianOfKingsBC());
	}
	
	public GuardianOfKings(Minion m) {
		super(m);
	}
	
}
