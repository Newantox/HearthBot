package Game.Cards.Spells.Untargetted;

import java.util.ArrayList;

import Game.BoardState;
import Game.MyTurnState;
import Game.Minions.Minion;

public class Equality extends UntargettedSpell {
	
	public Equality() {
		super("Equality", 2);
	}

	@Override
	public MyTurnState playCard(BoardState oldstate, int target) {
		ArrayList<Minion> newOppSide = new ArrayList<Minion>();
		ArrayList<Minion> newMySide = new ArrayList<Minion>();
		
		for(int position : oldstate.getPositionsInPlayOrder()) {
			Minion minion;
			if (position<7) minion = (oldstate.getMySide()).get(position);
			else minion = (oldstate.getOppSide()).get(position-7);
			
			Minion newMinion = new Minion(minion);
			newMinion.setHP(1);
	
			if (position<7) newMySide.set(position,newMinion);
			else newOppSide.set(position-7, newMinion);
		}
		
		return new BoardState(oldstate.getViewType(),oldstate.getHero(),oldstate.getEnemy(),newOppSide,newMySide,oldstate.getPositionsInPlayOrder(),oldstate.getEnemyHandSize());
	}
	
}
