package Game.EndTurnEffects;

import Game.MyTurnState;
import Game.TargetsType;
import Game.Heroes.Hero;
import Game.Minions.Minion;

public class RagnarosET extends EndTurnEffect {

	@Override
	public MyTurnState perform(MyTurnState oldstate, Hero hero, Minion source) {
		if (hero.getMyPos()==14 && source.getMyPos()<7) return oldstate.damageRandomHittable(TargetsType.ENEMYCHAR,8);
		else if (hero.getMyPos()==15 && source.getMyPos()>=7) return oldstate.damageRandomHittable(TargetsType.ALLYCHAR,8);
		else return oldstate;
	}

}
