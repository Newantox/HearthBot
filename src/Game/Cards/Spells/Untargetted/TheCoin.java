package Game.Cards.Spells.Untargetted;

import Game.BoardState;
import Game.Heroes.Hero;
import Search.State;

public class TheCoin extends UntargettedSpell {

	@Override
	public String getName() {
		return "The Coin";
	}

	@Override
	public int getCost() {
		return 0;
	}

	@Override
	public State playCard(BoardState oldstate, int target) {
		Hero hero = (oldstate.getHero()).fresh();
		hero.setCurrentMana(hero.getCurrentMana()+1);
		return new BoardState(hero, oldstate.getEnemy(),oldstate.getOppSide(),oldstate.getMySide(),oldstate.getMyDeck(),oldstate.getMyHand(),oldstate.getSummonEffects(),oldstate.getEnemyHandSize());
	}

}
