package Game.EndTurnEffects;

import java.util.ArrayList;

import Game.MyTurnState;
import Game.TargetsType;
import Game.Heroes.Hero;
import Game.Minions.Minion;

public class RagnarosET extends EndTurnEffect {

	@Override
	public MyTurnState perform(MyTurnState oldstate, TargetsType side, Minion source) {
		if (side.equals(TargetsType.ALLYCHAR) && source.getSide().equals(TargetsType.ALLYMINIONS)) return oldstate.damageRandomHittable(TargetsType.ENEMYCHAR,8);
		else if (side.equals(TargetsType.ENEMYCHAR) && source.getSide().equals(TargetsType.ENEMYMINIONS)) return oldstate.damageRandomHittable(TargetsType.ALLYCHAR,8);
		else return oldstate;
	}

}
