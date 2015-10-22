package Game.SummonEffects;

import Game.MyTurnState;
import Game.PlayableCard;
import Game.Buffs.AttributeBuff;
import Game.Minions.Minion;

public class WarsongCommanderSE extends SummonEffect {
	
	public MyTurnState perform(MyTurnState oldstate, PlayableCard source, Minion minion) {
		if (minion.getAtk()<=3) {
			return oldstate.applyBuff(minion.getId(), new AttributeBuff(-1,1,0,0,0,0,0,0,0));
		}
		else return oldstate;
	}
}
