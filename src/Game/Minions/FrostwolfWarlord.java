package Game.Minions;

import Game.Battlecrys.FrostwolfWarlordBC;

public class FrostwolfWarlord extends Minion {

	public FrostwolfWarlord() {
		super("Frostwolf Warlord",-1,5,4,4);
		battlecrys.add(new FrostwolfWarlordBC());
	}
	
	public FrostwolfWarlord(Minion m) {
		super(m);
	}
	
}
