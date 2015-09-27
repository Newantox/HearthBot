package Game.Minions;

import Game.Deathrattles.WebspinnerDR;

public class Webspinner extends Minion {

	public Webspinner(int target) {
		super("Webspinner",target,1,1,1);
		setRace(Race.BEAST);
		addDeathrattle(new WebspinnerDR());
	}
	
	public Webspinner(Minion m) {
		super(m);
	}
	
}