package Game.Minions;

import Game.Battlecrys.MurlocTidehunterBC;

public class MurlocTidehunter extends Minion {

	public MurlocTidehunter(int target) {
		super("Murloc Tidehunter",target,2,2,1,1);
		battlecrys.add(new MurlocTidehunterBC());
		
	}
	
	public MurlocTidehunter(Minion m) {
		super(m);
	}

}
