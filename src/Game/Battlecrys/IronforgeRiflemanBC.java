package Game.Battlecrys;

import java.util.LinkedHashSet;
import java.util.Set;

import Game.BoardState;
import Game.ChoiceState;
import Game.MyTurnState;
import Game.Actions.ChoiceAction;
import Game.Minions.Minion;
import Search.Action;

public class IronforgeRiflemanBC extends MinionBattlecry {

	@Override
	public MyTurnState perform(Minion minion, BoardState oldstate) {
		Set<Action> actions = new LinkedHashSet<Action>();
		for (int position : oldstate.getPositionsInPlayOrder()) {
			if (position<7) {
				if (((oldstate.getMySide()).get(position)).isTargettable()) actions.add(new IronforgeRiflemanChoice(position));
			}
			
			else if (((oldstate.getOppSide()).get(position-7)).isTargettable()) actions.add(new IronforgeRiflemanChoice(position));
		}
		actions.add(new IronforgeRiflemanChoice(14));
		actions.add(new IronforgeRiflemanChoice(15));
		
		if (actions.size()==0) return oldstate;
		return new ChoiceState(oldstate,actions);
	}
	
	
	public class IronforgeRiflemanChoice extends ChoiceAction {
		
		private int target;
		
		public IronforgeRiflemanChoice(int target) {
			this.target = target;
		}

		@Override
		public MyTurnState result(BoardState oldstate) {
			
			if (target<7) return ((oldstate.getMySide()).get(target)).damage(oldstate,1);
			else if (target<14) return ((oldstate.getOppSide()).get(target-7)).damage(oldstate,1);
			
			else if (target==14) return (oldstate.getHero()).damage(oldstate,1);
			else return (oldstate.getEnemy()).damage(oldstate,1);
		}

		@Override
		public void print() {
			System.out.println("Ironforge Rifleman damages minion at "+target);
			
		}
	}

}
