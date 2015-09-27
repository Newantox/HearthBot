package Game.Minions;

import Game.Battlecrys.GuardianOfKingsBC;

public class GuardianOfKings extends Minion {

	public GuardianOfKings(int target) {
		super("Guardian Of Kings",target,7,5,6);
		battlecrys.add(new GuardianOfKingsBC());
	}
	
	public GuardianOfKings(Minion m) {
		super(m);
	}
	
}
