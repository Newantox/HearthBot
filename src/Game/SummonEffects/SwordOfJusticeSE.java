package Game.SummonEffects;

import Game.BoardState;
import Game.Minions.Minion;
import Game.Weapons.Weapon;
import Search.State;

public class SwordOfJusticeSE extends SummonEffect {
	
	private int side;
	
	public SwordOfJusticeSE(int side) {
		this.side = side;
	}

	@Override
	public State perform(State oldstate, Minion minion) {
		BoardState state = (BoardState) oldstate;
		if (minion.getMyPos() < 7 && side==14) {
			BoardState tempstate = minion.changeAtk(state, 1);
			tempstate = (tempstate.getMySide()[minion.getMyPos()]).changeHP(tempstate, 1);

			Weapon weapon = (tempstate.getHero()).getWeapon();
			return weapon.changeDurability(tempstate,side,-1);
		}
			
		else if (minion.getMyPos() >= 7 && side==15) {
			BoardState tempstate = minion.changeAtk(state, 1);
			tempstate = (tempstate.getOppSide()[minion.getMyPos()-7]).changeHP(tempstate, 1);

			Weapon weapon = (tempstate.getEnemy()).getWeapon();
			return weapon.changeDurability(tempstate,side,-1);
		}
		
		else return oldstate;
	}

}
