package Game.Cards.Spells.Untargetted;

import Game.BoardState;
import Game.MyTurnState;

public class DivineFavour extends UntargettedSpell {

	public DivineFavour() {
		super("Divine Favour", 3);
	}

	@Override
	public MyTurnState playCard(BoardState oldstate, int target) {
		int amount = Math.max(0,oldstate.getEnemyHandSize() - (oldstate.getHero()).getMyHandSize());
		MyTurnState tempstate = oldstate;
		for (int i = 0; i<amount; i++) {
			tempstate = tempstate.drawCard();
		}
		return tempstate;
	}

}
