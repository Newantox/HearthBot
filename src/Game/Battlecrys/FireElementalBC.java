package Game.Battlecrys;

import java.util.LinkedHashSet;
import java.util.Set;

import Game.BoardState;
import Game.ChoiceState;
import Game.MyTurnState;
import Game.Actions.ChoiceAction;
import Game.Minions.Minion;
import Search.Action;

public class FireElementalBC extends MinionBattlecry {

	@Override
	public MyTurnState perform(Minion minion, BoardState oldstate) {
		Set<Action> actions = new LinkedHashSet<Action>();
		for (int position : oldstate.getPositionsInPlayOrder()) {
			if (position<7) {
				if (((oldstate.getMySide()).get(position)).isTargettable()) actions.add(new FireElementalChoice(position));
			}
			
			else if (((oldstate.getOppSide()).get(position-7)).isTargettable()) actions.add(new FireElementalChoice(position));
		}
		actions.add(new FireElementalChoice(14));
		actions.add(new FireElementalChoice(15));
		
		if (actions.size()==0) return oldstate;
		return new ChoiceState(oldstate,actions);
	}
	
	
	public class FireElementalChoice extends ChoiceAction {
		
		private int target;
		
		public FireElementalChoice(int target) {
			this.target = target;
		}

		@Override
		public MyTurnState result(BoardState oldstate) {
			
			if (target<7) return ((oldstate.getMySide()).get(target)).damage(oldstate,3);
			else if (target<14) return ((oldstate.getOppSide()).get(target-7)).damage(oldstate,3);
			
			else if (target==14) return (oldstate.getHero()).damage(oldstate,3);
			else return (oldstate.getEnemy()).damage(oldstate,3);
		}

		@Override
		public void print() {
			System.out.println("Fire Elemental damages minion at "+target);
			
		}

	}

}
