package Game.Battlecrys;

import java.util.LinkedHashSet;
import java.util.Set;

import Game.BoardState;
import Game.ChoiceState;
import Game.MyTurnState;
import Game.Actions.ChoiceAction;
import Game.Minions.Minion;
import Search.Action;

public class ShatteredSunClericBC extends MinionBattlecry {
	
	@Override
	public MyTurnState perform(Minion minion, BoardState oldstate) {
		Set<Action> actions = new LinkedHashSet<Action>();
		for (int position : oldstate.getPositionsInPlayOrder()) {
			if (position<7) {
				if (((oldstate.getMySide()).get(position)).isTargettable()) actions.add(new ShatteredSunClericChoice(minion,position));
			}	
		}
		if (actions.size()==0) return oldstate;
		else return new ChoiceState(oldstate,actions);
	}
	
	
	public class ShatteredSunClericChoice extends ChoiceAction {
		
		private int target;
		
		public ShatteredSunClericChoice(Minion source, int target) {
			this.target = target;
		}

		@Override
		public MyTurnState result(BoardState oldstate) {
			
			Minion minion = (oldstate.getMySide()).get(target);

			return oldstate.changeAtkHP(minion, 1, 1);
			    
		}

		@Override
		public void print() {
			System.out.println("Shattered Sun Cleric buffs minion at "+target);
			
		}
	}

}
