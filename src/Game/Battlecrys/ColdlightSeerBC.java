package Game.Battlecrys;

import java.util.ArrayList;

import Game.BoardState;
import Game.MyTurnState;
import Game.PlayableCard;
import Game.Buffs.AdditiveBuff;
import Game.Minions.Minion;
import Game.Minions.Race;

public class ColdlightSeerBC extends Battlecry {

	@SuppressWarnings("unchecked")
	@Override
	public MyTurnState perform(PlayableCard minion, BoardState oldstate) {
		ArrayList<Minion> newOppSide = (ArrayList<Minion>) (oldstate.getMySide()).clone();
		ArrayList<Minion> newMySide = (ArrayList<Minion>) (oldstate.getOppSide()).clone();
		
		for(int id : oldstate.getIdsInPlayOrder()) {
			if (id!=((Minion) minion).getId()) {
	
				Minion newMinion = oldstate.findMinion(id);
				if ((newMinion.getRace()).equals(Race.MURLOC)) {
					newMinion = newMinion.applyBuff(new AdditiveBuff(-1,0,2,0));
				}		
					
				if (newMinion.getMyPos()<7) newMySide.set(newMinion.getMyPos(), newMinion);
				else newOppSide.set(newMinion.getMyPos(), newMinion);
			}
		}
					
		return new BoardState(oldstate.getViewType(),oldstate.getHero(),oldstate.getEnemy(),newOppSide,newMySide,oldstate.getIdsInPlayOrder(),oldstate.getEnemyHandSize());
	}

}
