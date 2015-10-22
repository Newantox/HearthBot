package Game.Minions;

import Game.Battlecrys.MinionCompanionBC;

public class RazorfenHunter extends Minion {

	public RazorfenHunter() {
		super("Razorfen Hunter",-1,3,2,3);
		battlecrys.add(new MinionCompanionBC(new Boar()));
	}
	
	public RazorfenHunter(Minion m) {
		super(m);
	}
	
}