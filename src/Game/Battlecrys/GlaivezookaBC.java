package Game.Battlecrys;

import java.util.LinkedList;
import java.util.List;

import Game.BoardState;
import Game.MyTurnState;
import Game.PlayableCard;
import Game.RandomState;
import Game.StateProbabilityPair;
import Game.Buffs.AdditiveBuff;
import Game.Minions.Minion;

public class GlaivezookaBC extends Battlecry {

	@Override
	public MyTurnState perform(PlayableCard weapon, BoardState oldstate) {
		if (oldstate.numberOfAlliedMinions() == 0) return oldstate;
		else {
			List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
			for (Minion minion : oldstate.getMySide()) {
				list.add(new StateProbabilityPair(oldstate.applyBuff(minion.getId(),new AdditiveBuff(-1,1,0,0)), (double)1/oldstate.numberOfAlliedMinions(), minion.getName()+" gets buffed by Glaivezooka"));
			}
			return new RandomState(list);
		}
	}

}
