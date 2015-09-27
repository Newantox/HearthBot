package Game.Minions;

import Game.Battlecrys.DarkscaleHealerBC;

public class DarkscaleHealer extends Minion {

	public DarkscaleHealer(int target) {
		super("Darkscale Healer",target,5,4,5);
		battlecrys.add(new DarkscaleHealerBC());
	}
	
	public DarkscaleHealer(Minion m) {
		super(m);
	}
	
}