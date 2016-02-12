package Game.SummonEffects;

import Game.MyTurnState;
import Game.PlayableCard;
import Game.TargetsType;
import Game.Buffs.AdditiveBuff;
import Game.Minions.Minion;
import Game.Weapons.Weapon;

public class SwordOfJusticeSE extends SummonEffect {
	
	public SwordOfJusticeSE() {
	}

	@Override
	public MyTurnState perform(MyTurnState oldstate, PlayableCard weapon, Minion minion, TargetsType side) {
		MyTurnState tempstate = oldstate.applyBuff(minion.getId(),minion.getName(),new AdditiveBuff(-1,1,1,0));
		
		if (side.equals(TargetsType.ALLYCHAR)) {
			return tempstate.changeHeroWeaponAtkDurability(((Weapon) weapon).getId(),0,-1);
		}
			
		else {
			return tempstate.changeEnemyWeaponAtkDurability(((Weapon) weapon).getId(),0,-1);
		}
	}

	@Override
	public TargetsType getEffectRange() {
		return TargetsType.ALLYMINIONS;
	}

}
