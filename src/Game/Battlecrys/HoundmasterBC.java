package Game.Battlecrys;

import java.util.LinkedHashSet;
import java.util.Set;

import Game.BoardState;
import Game.ChoiceState;
import Game.MyTurnState;
import Game.PlayableCard;
import Game.Actions.ChoiceAction;
import Game.Buffs.AdditiveBuff;
import Game.Buffs.AttributeBuff;
import Game.Minions.Minion;
import Game.Minions.Race;
import Search.Action;

public class HoundmasterBC extends Battlecry {
	
	@Override
	public MyTurnState perform(PlayableCard minion, BoardState oldstate) {
		Set<Action> actions = new LinkedHashSet<Action>();
		for (int position : oldstate.getPositionsInPlayOrder()) {
			if (position<7) {
				if (((oldstate.getMySide()).get(position)).isTargettable() && ((oldstate.getMySide()).get(position)).getRace()==Race.BEAST) actions.add(new HoundmasterChoice((Minion) minion,position));
			}	
		}
		if (actions.size()==0) return oldstate;
		else return new ChoiceState(oldstate,actions);
	}
	
	
	public class HoundmasterChoice extends ChoiceAction {
		
		private int target;
		
		public HoundmasterChoice(Minion source, int target) {
			this.target = target;
		}

		@Override
		public MyTurnState result(BoardState oldstate) {
			
			Minion minion = (oldstate.getMySide()).get(target);
			
			MyTurnState tempstate = oldstate.applyBuff(minion.getId(), new AttributeBuff(-1,0,0,1,0,0,0,0,0));
			
			return tempstate.applyBuff(minion.getId(), new AdditiveBuff(-1,2,2,0));
			    
		}

		@Override
		public void print() {
			System.out.println("Houndmaster buffs minion at "+target);
			
		}
	}

}
