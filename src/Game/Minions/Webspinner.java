package Game.Minions;

import Game.Deathrattles.WebspinnerDR;

public class Webspinner extends Minion {

	public Webspinner() {
		super("Webspinner",-1,1,1,1);
		setRace(Race.BEAST);
		addDeathrattle(new WebspinnerDR());
	}
	
	public Webspinner(Minion m) {
		super(m);
	}
	
}