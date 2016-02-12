package Game.SummonEffects;

import Game.MyTurnState;
import Game.PlayableCard;
import Game.TargetsType;
import Game.Buffs.AdditiveBuff;
import Game.Minions.Minion;
import Game.Minions.Race;

public class MurlocTidecallerSE extends SummonEffect {
	
	public MyTurnState perform(MyTurnState oldstate, PlayableCard source, Minion minion, TargetsType side) {
		if (minion.getRace().equals(Race.MURLOC)) return oldstate.applyBuff(((Minion) source).getId(),((Minion) source).getName(),new AdditiveBuff(-1,1,0,0));
		else return oldstate;
	}

	@Override
	public TargetsType getEffectRange() {
		return TargetsType.ALLMINIONS;
	}
}
