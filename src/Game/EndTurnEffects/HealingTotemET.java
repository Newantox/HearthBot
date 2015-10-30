package Game.EndTurnEffects;

import java.util.ArrayList;

import Game.MyTurnState;
import Game.TargetsType;
import Game.Heroes.Hero;
import Game.Minions.Minion;

public class HealingTotemET extends EndTurnEffect {

	@Override
	public MyTurnState perform(MyTurnState oldstate, Hero hero, Minion source) {
		if (hero.getMyPos()==14 && source.getMyPos()<7) return oldstate.simultaneousHeal(TargetsType.ALLYMINIONS,1, new ArrayList<Minion>());
		else if (hero.getMyPos()==15 && source.getMyPos()>=7) return oldstate.simultaneousHeal(TargetsType.ENEMYMINIONS,1,new ArrayList<Minion>());
		else return oldstate;
	}

}
