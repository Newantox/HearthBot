package Game.Minions;

import Game.Auras.TimberWolfAura;

public class TimberWolf extends Minion {

	public TimberWolf() {
		super("Timber Wolf",-1,1,1,1);
		setRace(Race.BEAST);
		auras.add(new TimberWolfAura());
	}
	
	public TimberWolf(Minion m) {
		super(m);
	}
	
}