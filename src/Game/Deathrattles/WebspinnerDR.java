package Game.Deathrattles;

import java.util.LinkedList;
import java.util.List;

import Game.BoardState;
import Game.MyTurnState;
import Game.PlayableCard;
import Game.RandomState;
import Game.StateProbabilityPair;
import Game.TargetsType;
import Game.Heroes.Hero;
import Game.MinionLists.BeastCardList;

public class WebspinnerDR extends Deathrattle {

	@Override
	public MyTurnState perform(BoardState oldstate, PlayableCard minion, TargetsType side) {
		Hero hero;
		if (side.equals(TargetsType.ALLYCHAR)) hero = oldstate.getHero();
		else hero = oldstate.getEnemy();
		if (hero.getMyHandSize()<10) {
			BeastCardList beastCards = new BeastCardList();
			List<StateProbabilityPair> list = new LinkedList<StateProbabilityPair>();
			for (PlayableCard card : beastCards.get()) {
				list.add(new StateProbabilityPair(oldstate.addCardToHand(hero,card) , (double)1 / (beastCards.get()).size(), hero.getName()+" gets "+card.getName()+" from Webspinner DR"));
			}
			return new RandomState(list);
		}
		else return oldstate;
	}
	
}
