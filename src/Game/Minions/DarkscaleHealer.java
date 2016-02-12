package Game.Minions;

import Game.Battlecrys.DarkscaleHealerBC;

public class DarkscaleHealer extends Minion {

	public DarkscaleHealer() {
		super("Darkscale Healer",5,4,5);
		battlecrys.add(new DarkscaleHealerBC());
	}
	
	public DarkscaleHealer(Minion m) {
		super(m);
	}
	
}