package Game.Battlecrys;

import Game.BoardState;
import Game.Minions.Minion;
import Game.Minions.Race;
import Search.State;

public class ColdlightSeerBC extends MinionBattlecry {

	@Override
	public State perform(Minion minion, BoardState oldstate) {
		Minion[] newOppSide = new Minion[7];
		Minion[] newMySide = new Minion[7];
		for(int i = 0; i<7; i++) {
			if (oldstate.getOppSide()[i] != null ) {
				newOppSide[i] = new Minion(oldstate.getOppSide()[i]);
				if (newOppSide[i].getRace().equals(Race.MURLOC) && i!= minion.getMyPos()) newOppSide[i].setHP(newOppSide[i].getHP()+2);
			}
			if (oldstate.getMySide()[i] != null) {
				newMySide[i] = new Minion(oldstate.getMySide()[i]);
				if (newMySide[i].getRace().equals(Race.MURLOC) && i!= minion.getMyPos()) newMySide[i].setHP(newMySide[i].getHP()+2);
			}
		}
		return new BoardState(oldstate.getHero(),oldstate.getEnemy(),newOppSide,newMySide,oldstate.getMyDeck(),oldstate.getMyHand(),oldstate.getSummonEffects(),oldstate.getEnemyHandSize());
	}

}
