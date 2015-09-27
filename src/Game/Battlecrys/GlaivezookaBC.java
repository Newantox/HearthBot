package Game.Battlecrys;

import java.util.LinkedList;
import java.util.List;

import Game.BoardState;
import Game.MyTurnState;
import Game.RandomState;
import Game.StateProbabilityPair;
import Game.Minions.Minion;

public class GlaivezookaBC extends WeaponBattlecry {

	@Override
	public MyTurnState perform(BoardState oldstate) {
		if (oldstate.numberOfAlliedMinions() == 0) return oldstate;
		else {
			List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
			for (Minion minion : oldstate.getMySide()) {
				list.add(new StateProbabilityPair(minion.changeAtkHP(oldstate,1,0), (double)1/oldstate.numberOfAlliedMinions()));
			}
			return new RandomState(list);
		}
	}

}
