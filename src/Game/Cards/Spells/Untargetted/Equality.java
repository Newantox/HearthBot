package Game.Cards.Spells.Untargetted;

import Game.BoardState;
import Game.Minions.Minion;
import Search.State;

public class Equality extends UntargettedSpell {
	
	private int cost = 2;

	@Override
	public String getName() {
		return "Equality";
	}

	@Override
	public int getCost() {
		return cost;
	}

	@Override
	public State playCard(BoardState oldstate, int target) {
		Minion[] newOppSide = new Minion[7];
		Minion[] newMySide = new Minion[7];
		for(int i = 0; i<7; i++) {
			if (oldstate.getOppSide()[i] != null) {
				newOppSide[i] = new Minion(oldstate.getOppSide()[i]);
				newOppSide[i].setHP(1);
			}
			if (oldstate.getMySide()[i] != null) {
				newMySide[i] = new Minion(oldstate.getMySide()[i]);
				newMySide[i].setHP(1);
			}
		}
		return new BoardState(oldstate.getHero(),oldstate.getEnemy(),newOppSide,newMySide,oldstate.getMyDeck(),oldstate.getMyHand(),oldstate.getSummonEffects(),oldstate.getEnemyHandSize());
	}
	
}
