package Game.SummonEffects;

import Game.TargetsType;
import Game.Minions.Minion;
import Game.Minions.Race;
import Search.State;

public class MurlocTidecallerSE extends SummonEffect {
	
private Minion source;
	
	public MurlocTidecallerSE(Minion source) {
		this.source = source;
	}
	
	@Override
	public State perform(State oldstate, Minion minion) {
		if (minion.getRace().equals(Race.MURLOC)) return source.changeAtk(oldstate,1);
		else return oldstate;
	}

}
