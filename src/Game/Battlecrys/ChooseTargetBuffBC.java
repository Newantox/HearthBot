package Game.Battlecrys;

import java.util.LinkedHashSet;
import java.util.Set;

import Game.BoardState;
import Game.ChoiceState;
import Game.MyTurnState;
import Game.PlayableCard;
import Game.Actions.ChoiceAction;
import Game.Buffs.Buff;
import Game.Minions.Minion;
import Search.Action;

public class ChooseTargetBuffBC extends Battlecry {
	
	private Buff buff;
	
	public ChooseTargetBuffBC(Buff buff) {
		this.buff = buff;
	}
	
	@Override
	public MyTurnState perform(PlayableCard card, BoardState oldstate) {
		Set<Action> actions = new LinkedHashSet<Action>();
		for (int position : oldstate.getPositionsInPlayOrder()) {
			if (position<7) {
				if (((oldstate.getMySide()).get(position)).isTargettable()) actions.add(new TargetChoice(oldstate.getMySide().get(position)));
			}
			
			else if (((oldstate.getOppSide()).get(position-7)).isTargettable()) actions.add(new TargetChoice(oldstate.getOppSide().get(position-7)));
		}
		
		if (actions.size()==0) return oldstate;
		return new ChoiceState(oldstate,actions);
	}
	
	
	public class TargetChoice extends ChoiceAction {
		
		private Minion target;
		
		public TargetChoice(Minion target) {
			this.target = target;
		}
		
		@Override
		public MyTurnState result(BoardState oldstate) {
			return oldstate.applyBuff(target.getId(),buff);
		}

		@Override
		public void print() {
			System.out.println("Buff minion at "+target);
			
		}
		
	}
}
