package Game.SummonEffects;

import Game.MyTurnState;
import Game.PlayableCard;
import Game.TargetsType;
import Game.Buffs.AttributeBuff;
import Game.Minions.Minion;

public class WarsongCommanderSE extends SummonEffect {
	
	public MyTurnState perform(MyTurnState oldstate, PlayableCard source, Minion minion) {
		if (minion.getAtk()<=3) {
			return oldstate.applyBuff(minion.getId(),minion.getName(), new AttributeBuff(-1,1,0,0,0,0,0,0,0));
		}
		else return oldstate;
	}

	@Override
	public TargetsType getEffectRange() {
		// TODO Auto-generated method stub
		return null;
	}
}
