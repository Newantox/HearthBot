package Game.Deathrattles;

import java.util.LinkedList;
import java.util.List;

import Game.BoardState;
import Game.Card;
import Game.MyTurnState;
import Game.RandomState;
import Game.StateProbabilityPair;
import Game.MinionLists.BeastCardList;
import Game.MinionLists.MurlocList;
import Game.Minions.Minion;

public class WebspinnerDR extends MinionDeathrattle {

	@Override
	public MyTurnState perform(Minion minion, BoardState oldstate) {
		if (oldstate.numberOfAlliedMinions() < 7) {
			BeastCardList beastCards = new BeastCardList();
			List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
			for (Card card : beastCards.get()) {
				list.add(new StateProbabilityPair(oldstate.addCardToHand(hero,murloc) , (double)1 / (beastCards.get()).size()));
			}
			return new RandomState(list);
		}
		else return oldstate;
	}
	
}
