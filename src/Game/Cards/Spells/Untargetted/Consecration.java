package Game.Cards.Spells.Untargetted;

import java.util.ArrayList;

import Game.BoardState;
import Game.Minions.Minion;
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
		
		ArrayList<Minion> minions = new ArrayList<Minion>();
		ArrayList<Integer> amounts = new ArrayList<Integer>();
		
		int i = 0;
		while (oldstate.getOppSide()[i]!=null) {
			minions.add(oldstate.getOppSide()[i]);
			amounts.add(damage);
			i++;
		}
		return oldstate.simultaneousDamage(minions, amounts);
	}

}
