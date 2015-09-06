package Game.Weapons;

import Game.BoardState;
import Game.Minions.Minion;
import Search.State;

public class TruesilverChampion extends Weapon {
	
	public TruesilverChampion() {
		super("Truesilver Champion",4,4,2);
	}

	public TruesilverChampion(Weapon w) {
		super(w);
	}
	
	@Override
	public State attackWith(BoardState oldstate, Minion defender) {
		BoardState tempstate = (oldstate.getHero()).heal(oldstate,2);
		return super.attackWith(tempstate,defender);
		
	}

}
