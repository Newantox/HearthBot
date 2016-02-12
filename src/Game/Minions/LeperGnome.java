package Game.Minions;

import Game.Deathrattles.LeperGnomeDR;

public class LeperGnome extends Minion {

	public LeperGnome() {
		super("Leper Gnome",1,2,1);
		addDeathrattle(new LeperGnomeDR());
	}
	
	public LeperGnome(Minion m) {
		super(m);
	}

}
