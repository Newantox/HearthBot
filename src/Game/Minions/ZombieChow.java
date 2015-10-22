package Game.Minions;

import Game.Deathrattles.ZombieChowDR;

public class ZombieChow extends Minion {

	public ZombieChow() {
		super("Zombie Chow",-1,1,2,3);
		addDeathrattle(new ZombieChowDR());
	}
	
	public ZombieChow(Minion m) {
		super(m);
	}
	
}
