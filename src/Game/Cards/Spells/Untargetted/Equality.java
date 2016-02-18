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

	@SuppressWarnings("unchecked")
	@Override
	public MyTurnState playCard(BoardState oldstate, Character target) {
		ArrayList<Minion> newMySide = (ArrayList<Minion>) oldstate.getMySide().clone();
		ArrayList<Minion> newOppSide = (ArrayList<Minion>) oldstate.getOppSide().clone();
		
		for (int i = 0; i<newMySide.size(); i++) {
			newMySide.set(i, newMySide.get(i).applyBuff(new AdditiveBuff(-1,0,1-newMySide.get(i).getMaxHP(),0)));
		}
		
		for (int i = 0; i<newOppSide.size(); i++) {
			newOppSide.set(i, newOppSide.get(i).applyBuff(new AdditiveBuff(-1,0,1-newOppSide.get(i).getMaxHP(),0)));
		}
	
		
		return new BoardState(oldstate.getViewType(),oldstate.getHero(),oldstate.getEnemy(),newOppSide,newMySide,oldstate.getIdsInPlayOrder(),oldstate.getEnemyHandSize(),oldstate.isTurnEnded(),oldstate.getIdCounter());
	}
	
}
