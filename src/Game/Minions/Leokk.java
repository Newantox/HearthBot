package Game.Minions;

import Game.Auras.LeokkAura;

public class Leokk extends Minion {

	public Leokk() {
		super("Leokk",3,2,4);
		setRace(Race.BEAST);
		auras.add(new LeokkAura());
	}
	
	public Leokk(Minion m) {
		super(m);
	}
	
}