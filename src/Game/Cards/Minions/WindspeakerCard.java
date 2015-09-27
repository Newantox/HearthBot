package Game.Cards.Minions;

import Game.Minions.Windspeaker;
import Game.Minions.Minion;

public class WindspeakerCard extends MinionCard {

	public WindspeakerCard() {
		super("Windspeaker",4);
	}
	
	public Minion makeNew(int target) {
		return new Windspeaker(target);
	}

}