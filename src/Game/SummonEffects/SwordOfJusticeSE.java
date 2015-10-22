package Game.SummonEffects;

import Game.MyTurnState;
import Game.PlayableCard;
import Game.Buffs.AdditiveBuff;
import Game.Minions.Minion;
import Game.Weapons.Weapon;

public class SwordOfJusticeSE extends SummonEffect {
	
	private int side;
	
	public SwordOfJusticeSE(int side) {
		this.side = side;
	}

	@Override
	public MyTurnState perform(MyTurnState oldstate, PlayableCard weapon, Minion minion) {
		if (minion.getMyPos() < 7 && side==14) {
			MyTurnState tempstate = oldstate.applyBuff(minion.getId(),new AdditiveBuff(-1,1,1,0));
			return tempstate.changeHeroWeaponAtkDurability(((Weapon) weapon).getId(),0,-1);
		}
			
		else if (minion.getMyPos() >= 7 && side==15) {
			MyTurnState tempstate = oldstate.applyBuff(minion.getId(),new AdditiveBuff(-1,1,1,0));
			return tempstate.changeEnemyWeaponAtkDurability(((Weapon) weapon).getId(),0,-1);
		}
		
		else return oldstate;
	}

}
