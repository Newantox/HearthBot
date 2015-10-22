package Game.Minions;

import Game.Battlecrys.ChooseTargetDamageBC;

public class StormpikeCommando extends Minion {

	public StormpikeCommando() {
		super("Stormpike Commando",-1,5,4,2);
		battlecrys.add(new ChooseTargetDamageBC(2));
	}
	
	public StormpikeCommando(Minion m) {
		super(m);
	}
	
}