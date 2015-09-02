package Game.Cards.Spells.Untargetted;

import Game.BoardState;
import Search.State;

public class Consecration extends UntargettedSpell {
	int damage = 2;
	int cost = 4;

	@Override
	public String getName() {
		return "Consecration";
	}

	@Override
	public int getCost() {
		return cost;
	}

	@Override
	public State playCard(BoardState oldstate, int target) {
		
		BoardState tempstate = oldstate.getEnemy().damage(oldstate, damage);
		
		for (int i = 0; i<7; i++) {
			if (tempstate.getOppSide()[i] != null) {
				tempstate = ((tempstate.getOppSide())[i]).damage(tempstate,damage);
			}
		}
		
		return tempstate;
	}

}
