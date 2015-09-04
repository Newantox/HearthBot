package Game.Cards.Spells.Untargetted;

import Game.BoardState;
import Game.Minions.Minion;
import Game.Minions.SilverHandRecruit;
import Search.State;

public class MusterForBattle extends UntargettedSpell {

	int cost = 3;
	
	@Override
	public String getName() {
		return "Muster for Battle";
	}

	@Override
	public int getCost() {
		return cost;
	}

	@Override
	public State playCard(BoardState oldstate, int target) {
		int i = oldstate.numberOfMinions();
		Minion minion = new SilverHandRecruit(i);
		return minion.place(oldstate);
	}

}
