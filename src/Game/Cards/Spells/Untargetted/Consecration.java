package Game.Cards.Spells.Untargetted;

import java.util.ArrayList;

import Game.BoardState;
import Game.MyTurnState;
import Game.Minions.Minion;

public class Consecration extends UntargettedSpell {
	
	public Consecration() {
		super("Consecration", 4);
	}

	int damage = 2;

	@Override
	public MyTurnState playCard(BoardState oldstate, int target) {
		
		ArrayList<Minion> minions = new ArrayList<Minion>();
		ArrayList<Integer> amounts = new ArrayList<Integer>();
		
		for (Minion minion : oldstate.getOppSide()) {
			minions.add(minion);
			amounts.add(damage);
		}
		
		return oldstate.simultaneousSpellDamage(minions, amounts);
	}

}
