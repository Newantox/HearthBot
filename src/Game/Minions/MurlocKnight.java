package Game.Minions;

import Game.Inspires.MurlocKnightInspire;

public class MurlocKnight extends Minion {
	
	public MurlocKnight() {
		super("Murloc Knight",-1,4,3,4);
		setRace(Race.MURLOC);
		inspires.add(new MurlocKnightInspire());
	}
	
	public MurlocKnight(Minion m) {
		super(m);
	}

}
