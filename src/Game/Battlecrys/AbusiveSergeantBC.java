package Game.Battlecrys;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import Game.BoardState;
import Game.ChoiceState;
import Game.MyTurnState;
import Game.Actions.ChoiceAction;
import Game.Minions.Minion;
import Search.Action;

public class AbusiveSergeantBC extends MinionBattlecry {

	@Override
	public MyTurnState perform(Minion minion, BoardState oldstate) {
		Set<Action> actions = new LinkedHashSet<Action>();
		for (int position : oldstate.getPositionsInPlayOrder()) {
			if (position<7) {
				if (((oldstate.getMySide()).get(position)).isTargettable()) actions.add(new AbusiveSergeantChoice(position));
			}
			
			else if (((oldstate.getOppSide()).get(position-7)).isTargettable()) actions.add(new AbusiveSergeantChoice(position));
		}
		if (actions.size()==0) return oldstate;
		return new ChoiceState(oldstate,actions);
	}
	
	
	public class AbusiveSergeantChoice extends ChoiceAction {
		
		private int target;
		
		public AbusiveSergeantChoice(int target) {
			this.target = target;
		}

		@SuppressWarnings("unchecked")
		public MyTurnState result(BoardState oldstate) {
			Minion defender;
			
			if (target<7) {
				ArrayList<Minion> newMySide = (ArrayList<Minion>) (oldstate.getMySide()).clone();
				
				defender = new Minion((oldstate.getMySide()).get(target));
				
				defender.setTempAtkChange(defender.getTempAtkChange() + 2);
				newMySide.set(target, defender);
				
				return new BoardState(oldstate.getViewType(),oldstate.getHero(),oldstate.getEnemy(),oldstate.getOppSide(),newMySide,oldstate.getPositionsInPlayOrder(),oldstate.getEnemyHandSize());
			}
			
			else {
				ArrayList<Minion> newOppSide = (ArrayList<Minion>) (oldstate.getOppSide()).clone();
				
				defender = new Minion((oldstate.getOppSide()).get(target-7));
				
				defender.setTempAtkChange(defender.getTempAtkChange() + 2);
				newOppSide.set(target, defender);
				
			    return new BoardState(oldstate.getViewType(),oldstate.getHero(),oldstate.getEnemy(),newOppSide,oldstate.getMySide(),oldstate.getPositionsInPlayOrder(),oldstate.getEnemyHandSize());
			}
		}

		@Override
		public void print() {
			System.out.println("Abusive Sergeant buffs minion at "+target);
			
		}

	}

}
