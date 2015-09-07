package Game.Cards.Minions;

import Game.Minions.Minion;
import Game.Minions.MurlocTidecaller;

public class MurlocTidecallerCard extends MinionCard {
	
	public MurlocTidecallerCard(String name, int cost) {
		super("Murloc Tidecaller", 1);
	}

	@Override
	protected Minion makeNew(int target) {
		return new MurlocTidecaller(target);
	}

}
