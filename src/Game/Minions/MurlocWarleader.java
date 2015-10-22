package Game.Minions;

import Game.Auras.MurlocWarleaderAura;

public class MurlocWarleader extends Minion {
	public MurlocWarleader() {
		super("Murloc Warleader",-1,3,3,3);
		setRace(Race.MURLOC);
		auras.add(new MurlocWarleaderAura());
	}
	
	public MurlocWarleader(Minion m) {
		super(m);
	}

}
