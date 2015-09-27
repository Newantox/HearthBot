package Game.Cards.Spells.Untargetted;

import Game.BoardState;
import Game.MyTurnState;

public class TheCoin extends UntargettedSpell {

	public TheCoin() {
		super("The Coin", 0);
	}

	@Override
	public MyTurnState playCard(BoardState oldstate, int target) {
		return (oldstate.getHero()).useMana(oldstate, -1);
	}

}
