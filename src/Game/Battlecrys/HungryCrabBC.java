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
import Game.Minions.Race;
import Search.Action;

public class HungryCrabBC extends Battlecry {
	
	@Override
	public MyTurnState perform(PlayableCard minion, BoardState oldstate) {
		Set<Action> actions = new LinkedHashSet<Action>();
		for (int position : oldstate.getPositionsInPlayOrder()) {
			if (position<7) {
				if (((oldstate.getMySide()).get(position)).isTargettable() && ((oldstate.getMySide()).get(position)).getRace()==Race.MURLOC) actions.add(new HungryCrabChoice((Minion) minion,position));
			}
			
			else if (((oldstate.getOppSide()).get(position-7)).isTargettable() && ((oldstate.getMySide()).get(position)).getRace()==Race.MURLOC) actions.add(new HungryCrabChoice((Minion) minion,position));
		}
		if (actions.size()==0) return oldstate;
		else return new ChoiceState(oldstate,actions);
	}
	
	
	public class HungryCrabChoice extends ChoiceAction {
		
		private Minion source;
		private int target;
		
		public HungryCrabChoice(Minion source, int target) {
			this.source = source;
			this.target = target;
		}

		@Override
		public MyTurnState result(BoardState oldstate) {
			
			Minion defender;
			if (target<7) defender = (oldstate.getMySide()).get(target);
			else defender = (oldstate.getOppSide()).get(target-7);
				
			MyTurnState tempstate = defender.destroy(oldstate);	
			return tempstate.applyBuff(source.getId(), new AdditiveBuff(-1,2,2,0));
			    
		}

		@Override
		public void print() {
			System.out.println("Hungry Crab destroys murloc at "+target);
			
		}
	}

}
