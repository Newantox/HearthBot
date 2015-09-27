package Game.Cards.Minions;

import Game.Minions.Minion;
import Game.Minions.AngryChicken;

public class AngryChickenCard extends MinionCard {

	public AngryChickenCard() {
		super("Angry Chicken",1);
	}
	
	public Minion makeNew(int target) {
		return new AngryChicken(target);
	}

}