package Game.Minions;

import Game.Battlecrys.RazorfenHunterBC;

public class RazorfenHunter extends Minion {

	public RazorfenHunter(int target) {
		super("Razorfen Hunter",target,3,2,3);
		battlecrys.add(new RazorfenHunterBC());
	}
	
	public RazorfenHunter(Minion m) {
		super(m);
	}
	
}