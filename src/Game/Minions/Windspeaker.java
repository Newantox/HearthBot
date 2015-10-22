package Game.Minions;

import Game.Battlecrys.ChooseTargetBuffBC;
import Game.Buffs.AttributeBuff;

public class Windspeaker extends Minion {

	public Windspeaker() {
		super("Windspeaker",-1,4,3,3);
		battlecrys.add(new ChooseTargetBuffBC(new AttributeBuff(-1,0,0,0,0,1,0,0,0)));
	}
	
	public Windspeaker(Minion m) {
		super(m);
	}
	
}