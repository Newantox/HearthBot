package Game.Auras;

import Game.MyTurnState;
import Game.Minions.Minion;

public class FlametongueAura extends Aura {
	
	@Override
	public MyTurnState apply(MyTurnState oldstate, Minion source, Minion target) {
		/*int position = source.getMyPos();
		if (position==0) 
		if ((target.getMy()).equals(Race.MURLOC)) return oldstate.applyBuff(target.getId(),new AdditiveBuff(id,1,0,0));
		else return oldstate;*/
		return oldstate;
	}

}
