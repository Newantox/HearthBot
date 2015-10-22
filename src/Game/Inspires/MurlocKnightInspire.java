package Game.Inspires;

import java.util.LinkedList;
import java.util.List;

import Game.BoardState;
import Game.MyTurnState;
import Game.PlayableCard;
import Game.RandomState;
import Game.StateProbabilityPair;
import Game.MinionLists.MurlocList;
import Game.Minions.Minion;

public class MurlocKnightInspire extends Inspire {
	
	@Override
	public MyTurnState perform(PlayableCard minion, BoardState oldstate) {
		if (oldstate.numberOfAlliedMinions() < 7) {
			MurlocList murlocs = new MurlocList(((Minion) minion).getMyPos()+1);
			List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
			for (Minion murloc : murlocs.get()) {
				list.add(new StateProbabilityPair(oldstate.placeMinion(murloc) , (double)1 / (murlocs.get()).size()));
			}
			return new RandomState(list);
		}
		else return oldstate;
	}

}
