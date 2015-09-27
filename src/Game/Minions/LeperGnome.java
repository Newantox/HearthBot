package Game.Minions;

import Game.Deathrattles.LeperGnomeDR;

public class LeperGnome extends Minion {

	public LeperGnome(int target) {
		super("Leper Gnome",target,1,2,1);
		addDeathrattle(new LeperGnomeDR());
	}
	
	public LeperGnome(Minion m) {
		super(m);
	}

}
