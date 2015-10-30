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
		for (int id : oldstate.getIdsInPlayOrder()) {
			Minion targetMinion = oldstate.findMinion(id);
			
			if (targetMinion.isTargettable()) actions.add(new AbusiveSergeantChoice(id));
		}
		if (actions.size()==0) return oldstate;
		return new ChoiceState(oldstate,actions);
	}
	
	
	public class AbusiveSergeantChoice extends ChoiceAction {
		
		private int id;
		
		public AbusiveSergeantChoice(int id) {
			this.id = id;
		}

		public MyTurnState result(BoardState oldstate) {
			return oldstate.applyTempBuff(id,new AdditiveBuff(-1,2,0,0));
		}

		@Override
		public void print() {
			System.out.println("Abusive Sergeant buffs minion");
			
		}

	}

}
