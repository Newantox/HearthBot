package Game.Battlecrys;

import java.util.LinkedHashSet;
import java.util.Set;

import Game.BoardState;
import Game.ChoiceState;
import Game.MyTurnState;
import Game.Actions.ChoiceAction;
import Game.Minions.Minion;
import Game.Minions.Race;
import Search.Action;

public class HoundmasterBC extends MinionBattlecry {
	
	@Override
	public MyTurnState perform(Minion minion, BoardState oldstate) {
		Set<Action> actions = new LinkedHashSet<Action>();
		for (int position : oldstate.getPositionsInPlayOrder()) {
			if (position<7) {
				if (((oldstate.getMySide()).get(position)).isTargettable() && ((oldstate.getMySide()).get(position)).getRace()==Race.BEAST) actions.add(new HoundmasterChoice(minion,position));
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
				
			BoardState tempstate = minion.changeAttributes(oldstate,minion.isCharge(),minion.isDivineShield(),true,minion.isStealth(),minion.getWindfury(),minion.getSpelldamage(),minion.isFrozen());	
			
			minion = (tempstate.getMySide()).get(target);
			
			return tempstate.changeAtkHP(minion, 2, 2);
			    
		}

		@Override
		public void print() {
			System.out.println("Houndmaster buffs minion at "+target);
			
		}
	}

}
