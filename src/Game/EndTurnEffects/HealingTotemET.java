package Game.EndTurnEffects;

import java.util.ArrayList;

import Game.MyTurnState;
import Game.TargetsType;
import Game.Heroes.Hero;
import Game.Minions.Minion;

public class HealingTotemET extends EndTurnEffect {

	@Override
	public MyTurnState perform(MyTurnState oldstate, TargetsType side, Minion source) {
		if (side.equals(TargetsType.ALLYCHAR) && source.getSide().equals(TargetsType.ALLYMINIONS)) return oldstate.simultaneousHeal(TargetsType.ALLYMINIONS,1, new ArrayList<Minion>());
		else if (side.equals(TargetsType.ENEMYCHAR) && source.getSide().equals(TargetsType.ENEMYMINIONS)) return oldstate.simultaneousHeal(TargetsType.ENEMYMINIONS,1, new ArrayList<Minion>());
		else return oldstate;
	}

}
