package Game.Auras;

import Game.MyTurnState;
import Game.Buffs.AdditiveBuff;
import Game.Minions.Minion;
import Game.Minions.Race;

public class FlametongueAura extends Aura {
	
	private double id;

	public FlametongueAura() {
		id = Math.random();
	}
	
	@Override
	public MyTurnState apply(MyTurnState oldstate, Minion source, Minion target) {
		/*int position = source.getMyPos();
		if (position==0) 
		if ((target.getMy()).equals(Race.MURLOC)) return oldstate.applyBuff(target.getId(),new AdditiveBuff(id,1,0,0));
		else return oldstate;*/
		return oldstate;
	}

}
