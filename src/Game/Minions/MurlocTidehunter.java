package Game.Minions;

import Game.Battlecrys.MinionCompanionBC;

public class MurlocTidehunter extends Minion {

	public MurlocTidehunter() {
		super("Murloc Tidehunter",-1,2,2,1);
		setRace(Race.MURLOC);
		battlecrys.add(new MinionCompanionBC(new MurlocScout()));
		
	}
	
	public MurlocTidehunter(Minion m) {
		super(m);
	}

}
