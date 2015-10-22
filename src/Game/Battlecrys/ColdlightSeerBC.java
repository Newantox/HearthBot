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
		
		for(int position : oldstate.getPositionsInPlayOrder()) {
			if (position!=((Minion) minion).getMyPos()) {
				Minion subject;
				Minion newMinion;
				if (position<7) {
					subject = (oldstate.getMySide()).get(position);
					if ((subject.getRace()).equals(Race.MURLOC)) {
						newMinion = subject.applyBuff(new AdditiveBuff(-1,0,2,0));
						newMySide.set(position, newMinion);
					}
				}
				else {
					subject = (oldstate.getOppSide()).get(position-7);
					if ((subject.getRace()).equals(Race.MURLOC)) {
						newMinion = subject.applyBuff(new AdditiveBuff(-1,0,2,0));
						newOppSide.set(position-7, newMinion);
					}
				}
			}
		}
					
		return new BoardState(oldstate.getViewType(),oldstate.getHero(),oldstate.getEnemy(),newOppSide,newMySide,oldstate.getPositionsInPlayOrder(),oldstate.getEnemyHandSize());
	}

}
