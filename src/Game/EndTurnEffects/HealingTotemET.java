package Game.EndTurnEffects;

import java.util.ArrayList;

import Game.MyTurnState;
import Game.TargetsType;
import Game.Heroes.Hero;
import Game.Minions.Minion;

public class HealingTotemET extends EndTurnEffect {

	@Override
	public MyTurnState perform(MyTurnState oldstate, Hero hero) {
		if (hero.getMyPos()==14) return oldstate.simultaneousHeal(TargetsType.ALLYMINIONS,1, new ArrayList<Minion>());
		else return oldstate.simultaneousHeal(TargetsType.ENEMYMINIONS,1,new ArrayList<Minion>());
	}

}
