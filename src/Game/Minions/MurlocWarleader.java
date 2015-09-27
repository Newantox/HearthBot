package Game.Minions;

import Game.Auras.MurlocWarleaderAura;

public class MurlocWarleader extends Minion {
	public MurlocWarleader(int target) {
		super("Murloc Warleader",target,3,3,3);
		setRace(Race.MURLOC);
		auras.add(new MurlocWarleaderAura());
	}
	
	public MurlocWarleader(Minion m) {
		super(m);
	}

}
