package Game.Cards.Spells.Untargetted;

import Game.BoardState;
import Game.TargetsType;
import Search.State;

public class AvengingWrath extends UntargettedSpell {
	
	private int damage = 8;
	private int cost = 6;

	@Override
	public String getName() {
		return "Avenging Wrath";
	}

	@Override
	public int getCost() {
		return cost;
	}

	@Override
	public State playCard(BoardState oldstate, int target) {
	
		State tempstate = oldstate;
		for (int i = 0; i<damage; i++) {
			tempstate = tempstate.damageRandomHittable(TargetsType.ENEMYCHAR,1,1);
			
		}
		return tempstate;
	}

}
