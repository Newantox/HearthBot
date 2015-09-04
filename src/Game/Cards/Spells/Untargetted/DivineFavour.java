package Game.Cards.Spells.Untargetted;

import Game.BoardState;
import Search.State;

public class DivineFavour extends UntargettedSpell {

	int cost = 3;
	
	@Override
	public String getName() {
		return "Divine Favour";
	}

	@Override
	public int getCost() {
		return cost;
	}

	@Override
	public State playCard(BoardState oldstate, int target) {
		int amount = Math.max(0,oldstate.getEnemyHandSize() - (oldstate.getMyHand()).getSize());
		State tempstate = (State) oldstate;
		for (int i = 0; i<amount; i++) {
			tempstate = tempstate.drawCard();
		}
		return tempstate;
	}

}
