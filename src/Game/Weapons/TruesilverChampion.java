package Game.Weapons;

import Game.BoardState;
import Game.MyTurnState;
import Game.Minions.Minion;

public class TruesilverChampion extends Weapon {
	
	public TruesilverChampion() {
		super("Truesilver Champion",4,4,2);
	}

	public TruesilverChampion(Weapon w) {
		super(w);
	}
	
	@Override
	public MyTurnState attackWith(BoardState oldstate, Minion defender) {
		BoardState tempstate = (oldstate.getHero()).heal(oldstate,2);
		return super.attackWith(tempstate,defender);
		
	}

}
