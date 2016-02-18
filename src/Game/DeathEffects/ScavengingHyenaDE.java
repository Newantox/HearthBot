package Game.DeathEffects;

import Game.MyTurnState;
import Game.TargetsType;
import Game.Buffs.AdditiveBuff;
import Game.Minions.Minion;
import Game.Minions.Race;

public class ScavengingHyenaDE extends DeathEffect {
	
	public ScavengingHyenaDE() {
	}
	
	@Override
	public MyTurnState perform(MyTurnState oldstate, Minion source, Minion minion) {
		if (minion.getRace().equals(Race.BEAST)) return oldstate.applyBuff(source.getId(), new AdditiveBuff(-1,2,1,0));
		else return oldstate;
	}
	
	public TargetsType getEffectRange() {
		return TargetsType.ALLYMINIONS;
	}
	
}
