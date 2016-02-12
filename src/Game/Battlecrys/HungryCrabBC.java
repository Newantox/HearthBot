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
			Minion targetMinion = oldstate.findMinion(id,"");
			
			if (targetMinion.isTargettable() && targetMinion.getRace().equals(Race.MURLOC)) actions.add(new HungryCrabChoice((Minion) minion,id,targetMinion.getName()));
		}
		if (actions.size()==0) return oldstate;
		return new ChoiceState(oldstate,actions);
	}
	
	
	public class HungryCrabChoice extends ChoiceAction {
		
		private Minion source;
		private int id;
		private String name;
		
		public HungryCrabChoice(Minion source, int id, String name) {
			this.source = source;
			this.id = id;
			this.name = name;
		}

		@Override
		public MyTurnState result(BoardState oldstate) {
			
			Minion defender = oldstate.findMinion(id,name);
				
			MyTurnState tempstate = defender.destroy(oldstate);	
			return tempstate.applyBuff(source.getId(),source.getName(), new AdditiveBuff(-1,2,2,0));
			    
		}

		@Override
		public void print() {
			System.out.println("Hungry Crab destroys murloc");
			
		}
		
		@Override
		public String output() {
			return ("Abusive Sergeant buffs minion");
			
		}
	}

}
