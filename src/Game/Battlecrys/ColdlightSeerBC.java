package Game.Battlecrys;

import java.util.ArrayList;

import Game.BoardState;
import Game.MyTurnState;
import Game.Minions.Minion;
import Game.Minions.Race;

public class ColdlightSeerBC extends MinionBattlecry {

	@SuppressWarnings("unchecked")
	@Override
	public MyTurnState perform(Minion minion, BoardState oldstate) {
		ArrayList<Minion> newOppSide = (ArrayList<Minion>) (oldstate.getMySide()).clone();
		ArrayList<Minion> newMySide = (ArrayList<Minion>) (oldstate.getOppSide()).clone();
		
		for(int position : oldstate.getPositionsInPlayOrder()) {
			if (position!=minion.getMyPos()) {
				Minion subject;
				Minion newMinion;
				if (position<7) {
					subject = (oldstate.getMySide()).get(position);
					if ((subject.getRace()).equals(Race.MURLOC)) {
						newMinion = new Minion(subject);
						newMinion.setHP(newMinion.getHP()+2);
						newMySide.set(position, newMinion);
					}
				}
				else {
					subject = (oldstate.getOppSide()).get(position-7);
					if ((subject.getRace()).equals(Race.MURLOC)) {
						newMinion = new Minion(subject);
						newMinion.setHP(newMinion.getHP()+2);
						newOppSide.set(position-7, newMinion);
					}
				}
			}
		}
					
		return new BoardState(oldstate.getViewType(),oldstate.getHero(),oldstate.getEnemy(),newOppSide,newMySide,oldstate.getPositionsInPlayOrder(),oldstate.getEnemyHandSize());
	}

}
