package Game.Battlecrys;

import java.util.LinkedHashSet;
import java.util.Set;

import Game.BoardState;
import Game.ChoiceState;
import Game.Minions.Minion;
import Search.Action;
import Search.State;

public class AbusiveSergeantBC extends Battlecry {

	@Override
	public State perform(Minion minion, BoardState oldstate) {
		Set<Action> actions = new LinkedHashSet<Action>();
		for (int i = 0; i<7; i++) {
			if (oldstate.getMySide()[i] != null) actions.add(new AbusiveSergeantChoice(i));
		}
		for (int i = 7; i<14; i++) {
			if (oldstate.getOppSide()[i] != null) actions.add(new AbusiveSergeantChoice(i));
		}
		return new ChoiceState(oldstate,actions);
	}
	
	
	public class AbusiveSergeantChoice implements Action {
		
		private int target;
		
		public AbusiveSergeantChoice(int target) {
			this.target = target;
		}

		@Override
		public double cost() {
			return 0;
		}

		@Override
		public State result(BoardState oldstate) {
			Minion defender;
			if (target<7) {
				defender = new Minion(oldstate.getMySide()[target]);
				Minion[] newMySide = new Minion[7];
				for (int i = 0; i<7; i++) {
					if (oldstate.getMySide()[i] != null && i!=target) newMySide[i] = oldstate.getMySide()[i];
				}
				newMySide[target] = defender;
				
				defender.setTempAtkChange(defender.getTempAtkChange() + 2);
			
				return new BoardState(oldstate.getHero(),oldstate.getEnemy(),oldstate.getOppSide(),newMySide,oldstate.getMyDeck(),oldstate.getMyHand());
			}
			
			else {
				defender = oldstate.getOppSide()[target-7];
			    Minion[] newOppSide = new Minion[7];
			    for (int i = 0; i<7; i++) {
			    	if (oldstate.getOppSide()[i] != null && i!=target-7) newOppSide[i] = oldstate.getOppSide()[i];
			    }
			    newOppSide[target-7] = defender;
			    
			    defender.setTempAtkChange(defender.getTempAtkChange() + 2);
				
			    return new BoardState(oldstate.getHero(),oldstate.getEnemy(),newOppSide,oldstate.getMySide(),oldstate.getMyDeck(),oldstate.getMyHand());
			}
		}

		@Override
		public void print() {
			System.out.println("Abusive Sergeant buffs minion at "+target);
			
		}
	}

}
