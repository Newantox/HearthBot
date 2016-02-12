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
		for (int id : oldstate.getIdsInPlayOrder()) {
			Minion targetMinion = oldstate.findMinion(id,"");
			
			if (targetMinion.isTargettable() && targetMinion.getRace().equals(Race.BEAST)) actions.add(new HoundmasterChoice(id,targetMinion.getName()));
		}
		if (actions.size()==0) return oldstate;
		return new ChoiceState(oldstate,actions);
	}
	
	
	public class HoundmasterChoice extends ChoiceAction {
		
		private int id;
		private String name;
		
		public HoundmasterChoice(int id,String name) {
			this.id = id;
			this.name = name;
		}

		@Override
		public MyTurnState result(BoardState oldstate) {
			
			MyTurnState tempstate = oldstate.applyBuff(id,name, new AttributeBuff(-1,0,0,1,0,0,0,0,0));
			
			return tempstate.applyBuff(id,name, new AdditiveBuff(-1,2,2,0));
			    
		}

		@Override
		public void print() {
			System.out.println("Houndmaster buffs minion");
			
		}
		
		@Override
		public String output() {
			return ("Abusive Sergeant buffs minion");
			
		}
	}

}
