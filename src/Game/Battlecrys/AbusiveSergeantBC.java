package Game.Battlecrys;

import java.util.LinkedHashSet;
import java.util.Set;

import Game.BoardState;
import Game.ChoiceState;
import Game.MyTurnState;
import Game.PlayableCard;
import Game.Actions.ChoiceAction;
import Game.Buffs.AdditiveBuff;
import Game.Minions.Minion;
import Search.Action;

public class AbusiveSergeantBC extends Battlecry {

	@Override
	public MyTurnState perform(PlayableCard minion, BoardState oldstate) {
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

		public MyTurnState result(BoardState oldstate) {
			Minion minion = (oldstate.getMySide()).get(target);

			return oldstate.applyTempBuff(minion.getId(),new AdditiveBuff(-1,2,0,0));
		}

		@Override
		public void print() {
			System.out.println("Abusive Sergeant buffs minion at "+target);
			
		}

	}

}
