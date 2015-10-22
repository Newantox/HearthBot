package Game.Cards.Spells.Untargetted;

import java.util.ArrayList;

import Game.BoardState;
import Game.Character;
import Game.MyTurnState;
import Game.Buffs.AdditiveBuff;
import Game.Minions.Minion;

public class Equality extends UntargettedSpell {
	
	public Equality() {
		super("Equality", 2);
	}

	@Override
	public MyTurnState playCard(BoardState oldstate, Character target) {
		ArrayList<Minion> newOppSide = new ArrayList<Minion>();
		ArrayList<Minion> newMySide = new ArrayList<Minion>();
		
		for(int position : oldstate.getPositionsInPlayOrder()) {
			Minion minion;
			if (position<7) minion = (oldstate.getMySide()).get(position);
			else minion = (oldstate.getOppSide()).get(position-7);
			
			Minion newMinion = minion.applyBuff(new AdditiveBuff(-1,0,1-minion.getMaxHP(),0));
	
			if (position<7) newMySide.add(position,newMinion);
			else newOppSide.add(position-7, newMinion);
		}
		
		return new BoardState(oldstate.getViewType(),oldstate.getHero(),oldstate.getEnemy(),newOppSide,newMySide,oldstate.getPositionsInPlayOrder(),oldstate.getEnemyHandSize());
	}
	
}
