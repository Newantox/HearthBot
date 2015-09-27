package Game.Minions;

import Game.Battlecrys.HoundmasterBC;

public class Houndmaster extends Minion {

	public Houndmaster(int target) {
		super("Houndmaster",target,4,4,3);
		battlecrys.add(new HoundmasterBC());
	}
	
	public Houndmaster(Minion m) {
		super(m);
	}
	
}
