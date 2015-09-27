package Game.Minions;

import Game.Inspires.MurlocKnightInspire;

public class MurlocKnight extends Minion {
	
	public MurlocKnight(int target) {
		super("Murloc Knight",target,4,3,4);
		setRace(Race.MURLOC);
		inspires.add(new MurlocKnightInspire());
	}
	
	public MurlocKnight(Minion m) {
		super(m);
	}

}
