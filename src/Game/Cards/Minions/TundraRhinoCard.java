package Game.Cards.Minions;

import Game.Minions.TundraRhino;
import Game.Minions.Minion;

public class TundraRhinoCard extends MinionCard {

	public TundraRhinoCard() {
		super("Tundra Rhino",5);
	}
	
	public Minion makeNew(int target) {
		return new TundraRhino(target);
	}

}