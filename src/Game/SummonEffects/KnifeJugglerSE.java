package Game.SummonEffects;

import Game.TargetsType;
import Game.Minions.Minion;
import Search.State;

public class KnifeJugglerSE extends SummonEffect {

	private int side;
	
	public KnifeJugglerSE(int side) {
		this.side = side;
	}
	
	@Override
	public State perform(State oldstate, Minion minion) {
		if (side==14 && minion.getMyPos()<7) {
			return oldstate.damageRandomHittable(TargetsType.ENEMYCHAR, 1);
		}
		else if (side==15 && minion.getMyPos()>=7) {
			return oldstate.damageRandomHittable(TargetsType.ALLYCHAR, 1);
		}
		else return oldstate;
	}

}
