package Game.Deathrattles;

import java.util.LinkedList;
import java.util.List;

import Game.BoardState;
import Game.MyTurnState;
import Game.PlayableCard;
import Game.RandomState;
import Game.StateProbabilityPair;
import Game.Heroes.Hero;
import Game.MinionLists.BeastCardList;
import Game.Minions.Minion;

public class WebspinnerDR extends Deathrattle {

	@Override
	public MyTurnState perform(PlayableCard minion, BoardState oldstate) {
		Hero hero;
		if (((Minion) minion).getMyPos() < 7) hero = oldstate.getHero();
		else hero = oldstate.getEnemy();
		if (hero.getMyHandSize()<10) {
			BeastCardList beastCards = new BeastCardList();
			List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
			for (PlayableCard card : beastCards.get()) {
				list.add(new StateProbabilityPair(oldstate.addCardToHand(hero,card) , (double)1 / (beastCards.get()).size()));
			}
			return new RandomState(list);
		}
		else return oldstate;
	}
	
}
