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
		for (int id : oldstate.getIdsInPlayOrder()) {
			Minion targetMinion = oldstate.findMinion(id);
			
			if (targetMinion.isTargettable() && targetMinion.getRace().equals(Race.MURLOC)) actions.add(new HungryCrabChoice((Minion) minion,id));
		}
		if (actions.size()==0) return oldstate;
		return new ChoiceState(oldstate,actions);
	}
	
	
	public class HungryCrabChoice extends ChoiceAction {
		
		private Minion source;
		private int id;
		
		public HungryCrabChoice(Minion source, int id) {
			this.source = source;
			this.id = id;
		}

		@Override
		public MyTurnState result(BoardState oldstate) {
			
			Minion defender = oldstate.findMinion(id);
				
			MyTurnState tempstate = defender.destroy(oldstate);	
			return tempstate.applyBuff(source.getId(), new AdditiveBuff(-1,2,2,0));
			    
		}

		@Override
		public void print() {
			System.out.println("Hungry Crab destroys murloc");
			
		}
	}

}
