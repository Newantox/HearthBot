package Game.SummonEffects;

import Game.MyTurnState;
import Game.Minions.Minion;

public class SwordOfJusticeSE extends SummonEffect {
	
	private int side;
	
	public SwordOfJusticeSE(int side) {
		this.side = side;
	}

	@Override
	public MyTurnState perform(MyTurnState oldstate, Minion dummy, Minion minion) {
		if (minion.getMyPos() < 7 && side==14) {
			MyTurnState tempstate = oldstate.changeAtkHP(minion, 1, 1);
			return tempstate.changeHeroWeaponAtkDurability(0,-1);
		}
			
		else if (minion.getMyPos() >= 7 && side==15) {
			MyTurnState tempstate = oldstate.changeAtkHP(minion, 1, 1);
			return tempstate.changeEnemyWeaponAtkDurability(0,-1);
		}
		
		else return oldstate;
	}

}
