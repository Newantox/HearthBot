package Game.Minions;

import Game.Deathrattles.ZombieChowDR;

public class ZombieChow extends Minion {

	public ZombieChow(int target) {
		super("Zombie Chow",target,1,2,3);
		addDeathrattle(new ZombieChowDR());
	}
	
	public ZombieChow(Minion m) {
		super(m);
	}
	
}
