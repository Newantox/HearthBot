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
		
		for(int id : oldstate.getIdsInPlayOrder()) {
			Minion newMinion = oldstate.findMinion(id);
			
			newMinion = newMinion.applyBuff(new AdditiveBuff(-1,0,1-newMinion.getMaxHP(),0));
	
			if (newMinion.getMyPos()<7) newMySide.add(newMinion.getMyPos(),newMinion);
			else newOppSide.add(newMinion.getMyPos()-7, newMinion);
		}
		
		return new BoardState(oldstate.getViewType(),oldstate.getHero(),oldstate.getEnemy(),newOppSide,newMySide,oldstate.getIdsInPlayOrder(),oldstate.getEnemyHandSize());
	}
	
}
